package com.hernanbosqued.tests

import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock
import kotlin.annotation.AnnotationRetention.RUNTIME

infix fun <T> T?.ifNull(block: () -> Unit): Unit? {
    return if (this == null) block()
    else null
}



//{
//    return if (this == null) block()
//    else null
//}

@Target(AnnotationTarget.CLASS, AnnotationTarget.EXPRESSION)
@Retention(AnnotationRetention.SOURCE)
annotation class NullAnnotation

@Target(AnnotationTarget.CLASS)
@Retention(RUNTIME)
annotation class ConditionAnnotation(val condition: Boolean = false)

//@NullAnnotation(true)
//class returnNull : Any()
//
//@NullAnnotation(false)
//class returnInstance : Any()
//
//@ConditionAnnotation(true)
//class IfCondition : Any()
//
//@ConditionAnnotation(false)
//class ElseCondition : Any()
//
//




//infix fun <T> T.orElse(elseResult: T?): T? {
//    return if (this == null) elseResult else null
//}

//inline infix fun <reified T : Any> Boolean.then(ifResponse: T?): CONNECTOR<Any?> {
//
//    val newAnnotations = ArrayList<Annotation>()
//
//    var oldAnnotations: MutableList<Annotation> = (
//            ifResponse?.let {
//
//                newAnnotations.add(returnInstance()::class.java.getAnnotation(NullAnnotation::class.java))
//
//                newAnnotations.add(
//                    when (this) {
//                        true ->
//                            IfCondition()::class.java.getAnnotation(ConditionAnnotation::class.java)
//                        false ->
//                            ElseCondition()::class.java.getAnnotation(ConditionAnnotation::class.java)
//                    })
//
//                newAnnotations.add(returnInstance()::class.java.getAnnotation(NullAnnotation::class.java))
//                it
//
//            } ?: run {
//
//                newAnnotations.add(returnNull()::class.java.getAnnotation(NullAnnotation::class.java))
//                Any()
//            })::class.annotations as MutableList
//
//    oldAnnotations.removeAll { it is ConditionAnnotation || it is NullAnnotation }
//    oldAnnotations.addAll(newAnnotations)
//
//    return ifResponse!!
//}

//inline infix fun <reified T : Any> T.orElse(elseResponse: T?): T? {
//
//    return this::class.findAnnotation<ConditionAnnotation>()?.let {
//        if (!it.condition) elseResponse else null
//    }
//
//}

operator fun <T : Any> T.get(key: String, value: String) {
    println("$key: $value")
    System.console().printf("$key: $value")
}


inline infix fun <reified T : Any> T.log(str: String) {
    println("<" + this::class.java.simpleName + "-" + this.hashCode() + "> " + str)
}

inline infix fun <reified T : Any> T.lock(body: () -> T) {
    val lock = ReentrantLock()

    this log "inicio"

    try {
        this log "tratando de acceder OUTER"
        lock.tryLock(3, TimeUnit.SECONDS)
        this log "tratando de acceder INNER"
        lock.lock()
        this log "accedido"
        body()
    } catch (err: InterruptedException) {
        this log "interrumpido"
    } finally {
        lock.unlock()
        this log "fin"
    }
}


//IF
infix fun <T> Boolean.then(ifResult: T?): T? {
    return null
}

//ELSE
infix fun <T> T.orElse(elseResult: T?): T? {
    return null
}