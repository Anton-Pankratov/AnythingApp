package net.domain.di

import kotlin.reflect.KClass

/**
 * @author devlee
 *
 * Thanks him for this class
 */

object ServiceLocator {

    private val instances = mutableMapOf<KClass<*>, Any>()

    inline fun <reified T: Any> register(instance: T) = register(T::class, instance)

    fun <T: Any> register(kClass: KClass<T>, instance: T) {
        instances[kClass] = instance
    }

    @Suppress("UNCHECKED_CAST")
    fun <T: Any> get(kClass: KClass<T>): T = instances[kClass] as T
}

inline fun <reified T: Any> locate() = ServiceLocator.get(T::class)

inline fun <reified T: Any> locateLazy(): Lazy<T> = lazy { ServiceLocator.get(T::class) }