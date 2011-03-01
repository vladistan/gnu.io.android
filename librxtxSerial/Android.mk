


LOCAL_PATH := $(call my-dir)
include $(CLEAN_VARS)

LOCAL_MODULE_TAGS := eng
LOCAL_SRC_FILES := $(call all-subdir-java-files)
LOCAL_PACKAGE_NAME := GnuIOTest

LOCAL_SDK_VERSION := current

LOCAL_JNI_SHARED_LIBRARIES := librxtxSerial

include $(BUILD_PACKAGE)
include $(call all-makefiles-under,$(LOCAL_PATH))
