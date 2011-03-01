package gnuio.test;

import gnu.io.SerialPort;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TooManyListenersException;

final class TestSerialPort extends SerialPort {
	/**
	 * 
	 */
	private GNUIOTest TestSerialPort;

	/**
	 * @param test
	 */
	TestSerialPort(GNUIOTest test) {
		TestSerialPort = test;
	}

	@Override
	public void setOutputBufferSize(int size) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setInputBufferSize(int size) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isReceiveTimeoutEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isReceiveThresholdEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isReceiveFramingEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getReceiveTimeout() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getReceiveThreshold() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getReceiveFramingByte() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OutputStream getOutputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getOutputBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getInputBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void enableReceiveTimeout(int time)
			throws UnsupportedCommOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enableReceiveThreshold(int thresh)
			throws UnsupportedCommOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enableReceiveFraming(int f)
			throws UnsupportedCommOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disableReceiveTimeout() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disableReceiveThreshold() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disableReceiveFraming() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean setUARTType(String type, boolean test)
			throws UnsupportedCommOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setSerialPortParams(int b, int d, int s, int p)
			throws UnsupportedCommOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRTS(boolean state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean setParityErrorChar(byte b)
			throws UnsupportedCommOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setLowLatency() throws UnsupportedCommOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFlowControlMode(int flowcontrol)
			throws UnsupportedCommOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean setEndOfInputChar(byte b)
			throws UnsupportedCommOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setDivisor(int Divisor)
			throws UnsupportedCommOperationException, IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setDTR(boolean state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean setCallOutHangup(boolean NoHup)
			throws UnsupportedCommOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setBaudBase(int BaudBase)
			throws UnsupportedCommOperationException, IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void sendBreak(int duration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEventListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyOnRingIndicator(boolean enable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyOnParityError(boolean enable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyOnOverrunError(boolean enable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyOnOutputEmpty(boolean enable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyOnFramingError(boolean enable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyOnDataAvailable(boolean enable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyOnDSR(boolean enable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyOnCarrierDetect(boolean enable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyOnCTS(boolean enable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyOnBreakInterrupt(boolean enable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isRTS() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRI() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDTR() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDSR() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCTS() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCD() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getUARTType() throws UnsupportedCommOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getStopBits() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte getParityErrorChar() throws UnsupportedCommOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getParity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getLowLatency() throws UnsupportedCommOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getFlowControlMode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte getEndOfInputChar() throws UnsupportedCommOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDivisor() throws UnsupportedCommOperationException,
			IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDataBits() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getCallOutHangup() throws UnsupportedCommOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getBaudRate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBaudBase() throws UnsupportedCommOperationException,
			IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addEventListener(SerialPortEventListener lsnr)
			throws TooManyListenersException {
		// TODO Auto-generated method stub
		
	}
}