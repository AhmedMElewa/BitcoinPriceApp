#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring
Java_com_elewa_sampleandroidapp_core_keys_AppKeys_getBaseUrl(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF("https://api.coindesk.com");
}
