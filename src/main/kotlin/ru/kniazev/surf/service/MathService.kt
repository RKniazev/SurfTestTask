package ru.kniazev.surf.service

import org.springframework.stereotype.Service
import java.util.*

@Service
class MathService {
    fun calculate(input: String): String {
        val strWithoutBrackets = openBrackets(input)
        return math(strWithoutBrackets)
    }

    fun openBrackets(str: String):String{
        val stack = Stack<Int>()
        var arr = str.toCharArray()
        for (index in 0 until arr.size){
            when (arr[index]){
                '(' -> stack.add(index)
                ')' -> {
                    val res = str.substring(stack.pop()+1,index)
                    val result = MathService().math(res)
                    val result1 = str.replaceFirst("($res)",result)
                    return openBrackets(result1)
                }
            }
        }
        return str
    }

    fun math(string: String):String{
        when (findNextAction(string)){
            '*' -> return math(tmpAction(string,'*'))
            '/' -> return math(tmpAction(string,'/'))
            '+' -> return math(tmpAction(string,'+'))
            '-' -> return math(tmpAction(string,'-'))
        }
        return string
    }

    fun findNextAction(str: String):Char?{
        if (str.contains('/') or str.contains("*") ){
            str.forEach {
                if (it == '/' || it == '*'){ return it }
            }
        }
        str.forEach {
            if ((it == '-') or (it == '+')){ return it }
        }
        return null
    }

    fun tmpAction(str: String, act: Char):String{
        val parts = str.split(act)
        val num1 = findLast(parts[0])
        val num2 = findFirst(parts[1])
        return when(act){
            '*' -> str.replaceFirst("$num1$act$num2",(num1*num2).toString())
            '/' -> str.replaceFirst("$num1$act$num2",(num1/num2).toString())
            '-' -> str.replaceFirst("$num1$act$num2",(num1-num2).toString())
            '+' -> str.replaceFirst("$num1$act$num2",(num1+num2).toString())
            else -> ""
        }
    }

    fun findLast(str: String) : Int{
        val regexLast = "(\\d+)\$"
        regexLast.toRegex().find(str)?.value?.let {
            return it.toInt()
        }
        return 0
    }
    fun findFirst(str: String) : Int{
        val regexLast = "^(\\d+)"
        regexLast.toRegex().find(str)?.value?.let {
            return it.toInt()
        }
        return 0
    }
}
