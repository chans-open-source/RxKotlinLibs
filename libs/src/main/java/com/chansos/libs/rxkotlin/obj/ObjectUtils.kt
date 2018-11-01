package com.chansos.libs.rxkotlin.obj

import com.chansos.libs.rxkotlin.log.LogUtils
import java.lang.reflect.Field
import java.lang.reflect.Modifier

/**
 * 对象操作工具
 * 使用反射对对象进行操作
 * */
@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")
class ObjectUtils {
    companion object {
        /**
         * 给对象中的某个变量注入实例
         *
         * @param obj 操作对象
         * @param fieldName 变量名
         * @param fieldValue 变量值
         * */
        fun inject(obj: Any, fieldName: String, fieldValue: Any?) {
            try {
                val clazz = obj.javaClass
                try {
                    val field1 = clazz.getField(fieldName)
                    field1.set(obj, fieldValue)
                } catch (e: Exception) {
                    val field2 = clazz.getDeclaredField(fieldName)
                    field2.isAccessible = true
                    field2.set(obj, fieldValue)
                }
            } catch (e: Exception) {
                LogUtils.e(e)
            }
        }

        /**
         * 摧毁某个对象
         * 释放对象中的所有变量（置空）
         *
         * @param obj 操作对象
         * */
        fun destory(obj: Any) {
            val clazz = obj.javaClass
            clazz.declaredFields.forEach { field ->
                reset(obj, field)
            }
            clazz.fields.forEach { field ->
                reset(obj, field)
            }
        }

        /**
         * 置空对象中的某个变量
         *
         * @param obj 操作对象
         * @param field 变量名
         * */
        private fun reset(obj: Any, field: Field) {
            val name = field.name
            val type = field.type.name
            if (!(name == name.toUpperCase() || type == type.toLowerCase() || Modifier.isFinal(field.modifiers))) {
                inject(obj, name, null)
            }
        }
    }
}