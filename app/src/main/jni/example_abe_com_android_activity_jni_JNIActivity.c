#include <example_abe_com_android_activity_jni_JNIActivity.h>
#include <jni.h>
#include <stdio.h>

JNIEnv* jniEnv;

JNIEXPORT jstring JNICALL Java_example_abe_com_android_activity_jni_JNIActivity_getHelloContent
        (JNIEnv * env, jobject jThis){

    if (jniEnv == NULL){
        jniEnv = env;
    }

    //返回jstring类型内容
    return (*jniEnv)->NewStringUTF(jniEnv, "Hello from JNI in native_jni_activity");
}

JNIEXPORT void JNICALL Java_example_abe_com_android_activity_jni_JNIActivity_showHelloContent
        (JNIEnv * env, jobject jThis){

    if (jniEnv == NULL){
        jniEnv = env;
    }

    //获取类对象
    jclass  clazz = (*jniEnv)->FindClass(jniEnv, "example/abe/com/android/activity/jni/JNIActivity");
    if (clazz == NULL){
        printf("find class JNIActivity error!");
        return;
    }
    //获取方法：methodCalledByJni（"(Ljava/lang/String;)V"为方法签名）
    //详细知识看Android开发艺术探究
    jmethodID  id = (*jniEnv)->GetStaticMethodID(jniEnv, clazz, "methodCalledByJni", "(Ljava/lang/String;)V");
    if (id == NULL){
        printf("find method methodCalledByJni error!");
        return;
    }
    //函数参数内容
    jstring helloContent = Java_example_abe_com_android_activity_jni_JNIActivity_getHelloContent(jniEnv, jThis);
    (*jniEnv)->CallStaticVoidMethod(jniEnv, clazz, id, helloContent);
}