package gnuio.test;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TooManyListenersException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class GNUIOTest extends Activity {
	
	
    private final class SerialEventsListener implements SerialPortEventListener {
		private final HashMap<Integer, String> evtLabels;

		private SerialEventsListener() {
			evtLabels = new HashMap<Integer, String>();
			evtLabels.put ( SerialPortEvent.CTS, "CTS" );
			evtLabels.put ( SerialPortEvent.DSR, "DSR" );
			evtLabels.put ( SerialPortEvent.RI, "RI" );
			evtLabels.put ( SerialPortEvent.CD, "CD" );
		}

		@Override
		public void serialEvent(SerialPortEvent ev) {
			
			/// Create message bundle
			Bundle dBundle = new Bundle();
			Message msg = Message.obtain();
			
			/// Get Event type
			int t = ev.getEventType();
			Boolean v = ev.getNewValue();
			
			/// If it is one of the status events just forward it to the
			/// handler as is
			if ( evtLabels.containsKey(t))
			{
				dBundle.putBoolean(evtLabels.get(t), v);
			}	
			//TODO:  Not sure it does anything...
			//       data reading is handled by the separate thread
			else if ( t == SerialPortEvent.DATA_AVAILABLE  )
			{
				byte[] readBuffer = new byte[20];
				int numBytes;
				int numBytesTotal = 0;
				String sReadBuff = "";
				
				try {
					while (in.available() > 0) {
						numBytes = in.read(readBuffer);
						numBytesTotal += numBytes;
						String tmpR = new String(readBuffer);
						sReadBuff += tmpR.substring(0, numBytes); 
					}
					dBundle.putString("DATA", sReadBuff );
				} catch (IOException e) {
					dBundle.putString("ERR", "Error Reading from serial port" );
				}
			}
			else if ( t == SerialPortEvent.OUTPUT_BUFFER_EMPTY )
			{
				dBundle.putString("OUT", "Empty" );
			}
			else
			{
				dBundle.putString("ERR", "Unhandled COMM Event" );
			}
			
			msg.setData(dBundle);
			mHandler.sendMessage(msg);
			
		}
	}

	private SerialPort serialPort;
	private InputStream in;
	private OutputStream out;	
	private Map<String, View> indMap;
	private Handler mHandler;
	private Random rnd;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
    	
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        // Create Status Indicators   
       final String [] Signals =  new String[] { "CD", "CTS", "DSR", "RI" };        
       indMap =  createIndicators(Signals, (ViewGroup) findViewById(R.id.IndContainer));       
       rnd =  new java.util.Random();
        
       /// Find serial ports...
       CommPortIdentifier portId = null;  // will be set if port found
       Enumeration portIdentifiers = CommPortIdentifier.getPortIdentifiers();

       /// See what ports are available. and latch on desired port
 	   toast("Requesting Ports");
 	   while (portIdentifiers.hasMoreElements())
        {
           CommPortIdentifier pid = (CommPortIdentifier) portIdentifiers.nextElement();
           
            toast ( "Got : " + pid.getName() );            
            if(pid.getPortType() == CommPortIdentifier.PORT_SERIAL &&
               pid.getName().equals("/dev/ttyUSB4")) 
            {
                portId = pid;
                break;
            }
        }
 	   
 	   
 	   /// Bail out if we can't get the port..
 	   if ( portId == null )
 	   {
 		   toast ( "Can't find USB Serial Port ");
 		   return ;
 	   }
 	   

	   // Event Notification listener...
	   
		
		
		
	   final TextView rcvData  = (TextView) findViewById(R.id.RecvData); 
	   /// Handler object that receives events from read and
	   /// notification threads..
       mHandler = new Handler() {
    	   public void handleMessage(Message msg) 
    	   {
    		   Bundle d = msg.getData();
    		   
    		   /// Check if it is a status IND.  If it is .
    		   /// change the state of the inidicator
    		   for (String s : Signals) {
    			   if ( d.containsKey(s))
    			   {
    				   setInd(s, d.getBoolean(s));
    			   }    
    			}
    		   
    		   /// Notify user of the error message
    		   if ( d.containsKey("ERR"))
    		   {
    			   toast ( "Error: " + d.getString("ERR"));
    		   }
    		   
    		   /// Append data to the data area..
    		   if ( d.containsKey("DATA"))
    		   {   
    			   rcvData.append(d.getString("DATA"));
    		   }
    		   
    		   /// Notify user that sending is complete...
    		   if ( d.containsKey("OUT"))
    		   {
    			   toast ( "OUT " + d.getString("OUT"));
    		   }
    	   };
       };
       
      
      
        try {
        	
			 serialPort = (SerialPort) portId.open("GNU IO Test", 2000 );
			
			 in  = serialPort.getInputStream();
			 out = serialPort.getOutputStream();
			 
			 serialPort.notifyOnCarrierDetect(true);
			 serialPort.notifyOnCTS(true);
			 serialPort.notifyOnDSR(true);
			 serialPort.notifyOnRingIndicator(true);
			 serialPort.notifyOnDataAvailable(true);
			 serialPort.notifyOnOutputEmpty(true);
			 
			 
			 serialPort.addEventListener(new SerialEventsListener());
			 
			 
			 serialPort.setSerialPortParams(9600, 
					 					    SerialPort.DATABITS_8, 
					 					    SerialPort.STOPBITS_1, 
					 					    SerialPort.PARITY_NONE);
			 
			 serialPort.setFlowControlMode( SerialPort.FLOWCONTROL_RTSCTS_IN  | SerialPort.FLOWCONTROL_RTSCTS_OUT );
			 serialPort.setDTR(true);
			 serialPort.setRTS(true);
			 
			 
			 setInd("CD",  serialPort.isCD());
			 setInd("RI",  serialPort.isRI());
			 setInd("CTS", serialPort.isCTS());
			 setInd("DSR", serialPort.isDSR());
			 
		
        } catch (IOException e) {
        	String msg = "I/O Exception " + e.getMessage();
			toast(msg);
		}
		catch (PortInUseException e) {
			toast ( "Port in use by " + e.currentOwner );
	 	    
		} catch (UnsupportedCommOperationException e) {
			 toast ("Unsupported Operation " + e.getMessage());
        				
		} catch (TooManyListenersException e) {
			 toast ("Too many listeners");
		}
		
		
		// Reading Thread...
		new Thread () {
			public void run() 
			{
				
				for(;;)
				{
				int numBytes;
				int numBytesTotal = 0;
				String sReadBuff = "";
				
				try {
				
					// Read the data until no more available
					byte[] readBuffer = new byte[20];
				
					numBytes = in.read(readBuffer);
				
					numBytesTotal += numBytes;
					String tmpR = new String(readBuffer);
					sReadBuff += tmpR.substring(0, numBytes); 
				
				} 
				catch (IOException e) {
					Bundle dBundle = new Bundle();
					Message msg = Message.obtain();
					dBundle.putString("ERR", e.getMessage() );
					msg.setData(dBundle);
					mHandler.sendMessage(msg);
				}
				
				/// If any data was read ship it to the UI
				if ( sReadBuff.length() > 0 )
				{
					Bundle dBundle = new Bundle();
					Message msg = Message.obtain();
					dBundle.putString("DATA", sReadBuff );
					msg.setData(dBundle);
					mHandler.sendMessage(msg);
				}
				
				}
			
			}
		}.start();
				
		
		// Attach Send Button Handler
		findViewById(R.id.SendBtn).setOnClickListener(new Button.OnClickListener() {
    		public void onClick(View v) {
    			
    			// Get Data to Send...
    			EditText sDataField = (EditText) findViewById(R.id.SendData);
    			String txt = sDataField.getText().toString();
    			try {
    				
    				// Send Data...
        			toast("Sending " + txt );
        			out.write(txt.getBytes("UTF8"));
					out.write(0x0A);
					out.flush();
					
					// Clear Input Field
	    			sDataField.getText().clear();
	    			
	    			// Pop the toast
	    			toast("Sent");
					
				} catch (UnsupportedEncodingException e) {
					toast ( "Unsupported Encoding");
				} catch (IOException e) {
					toast("Sending IO Exception");
				}
    		}
    		});
    
    
    }

    
    // Create line of indicators
	private Map<String, View> createIndicators(final String[] Signals, ViewGroup indCnt) {
		   
		   LayoutParams lp = new LayoutParams(indCnt.getLayoutParams());
		   lp.width  = 40;
		   lp.height = 20;
		   HashMap<String, View> inds = new HashMap<String, View>();
		   
		   for (String s : Signals) {
			   TextView tv = new TextView(this,null,R.style.Ind);
		       tv.setText(s);
		       tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.ind_off));
		       tv.setLayoutParams(new LayoutParams(lp));           
		       indCnt.addView(tv);
		       inds.put(s, tv);
		   }
		   
		   return inds;
	}

	
	// Set Indicator
	private void setInd(String key, boolean b) {
		View v = indMap.get(key);
		if ( b )
			v.setBackgroundDrawable(getResources().getDrawable(R.drawable.ind_on));
		else
			v.setBackgroundDrawable(getResources().getDrawable(R.drawable.ind_off));	
		v.invalidate();
		toast( key + ( b ? "On" : "Off"));
	}

	// Show the toast notification..
	private void toast(String msg) {
		Toast t3 = Toast.makeText(this, msg, 100);
		t3.show();
	}
}
