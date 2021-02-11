package ru.kniazev.surf.service

import org.springframework.stereotype.Service
import java.util.*

@Service
class MathService {
    fun calculate(input: String): String {
        val strWithoutBrackets = openBrackets(input)
        val result = math(strWithoutBrackets)
        return deleteSpecSymbolForNegative(result)
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
        if (isNegative(string)){
            if (string.contains("[") && string.contains("]")){
                return string
            }else{
                return addSpecSymbolForNegative(string.toInt())
            }

        }
        when (findNextAction(string)){
            '*' -> return math(tmpAction(string,'*'))
            '/' -> return math(tmpAction(string,'/'))
            '+' -> return math(tmpAction(string,'+'))
            '-' -> return math(tmpAction(string,'-'))
        }
        return string
    }

    fun findNextAction(inpStr: String):Char?{
        val str = "([\\[]-(\\d+)[\\]])".toRegex().replaceFirst(inpStr,"")
        str.forEach {
            if (it == '/' || it == '*'){ return it }
        }
        str.forEach {
            if ((it == '-') or (it == '+')){ return it }
        }
        return null
    }

    fun tmpAction(str: String, act: Char):String{
        val parts = str.replace("[-","[[").split(act)
        if (isNegative(str)){
            return addSpecSymbolForNegative(str.toInt())
        }
        var num1 = findLast(parts[0].replace("[[","[-"))
        var num2 = findFirst(parts[1].replace("[[","[-"))


        return when(act){
            '*' -> str.replaceFirst(oldPartOfStr(num1,act,num2),addSpecSymbolForNegative((num1*num2)))
            '/' -> str.replaceFirst(oldPartOfStr(num1,act,num2),addSpecSymbolForNegative((num1/num2)))
            '-' -> str.replaceFirst(oldPartOfStr(num1,act,num2),addSpecSymbolForNegative((num1-num2)))
            '+' -> str.replaceFirst(oldPartOfStr(num1,act,num2),addSpecSymbolForNegative((num1+num2)))
            else -> ""
        }
    }

    fun oldPartOfStr(num1: Int, act: Char, num2: Int)= "${addSpecSymbolForNegative(num1)}$act${addSpecSymbolForNegative(num2)}"


    fun findLast(str: String) : Int{
        val regexLast = "([\\[]-(\\d+)[\\]]\$)|((\\d+)\$)"
        regexLast.toRegex().find(str)?.value?.let {
            return deleteSpecSymbolForNegative(it).toInt()
        }
        return 0
    }
    fun findFirst(str: String) : Int{
        val regexLast = "(^(\\d+))|^([\\[]-(\\d+)[\\]])"
        regexLast.toRegex().find(str)?.value?.let {
            return deleteSpecSymbolForNegative(it).toInt()
        }
        return 0
    }

    fun isNegative(str: String):Boolean{
        val regexNegative = "-(\\d+)".toRegex()
        val regexNegativeWithBrackets = "[\\[]-(\\d+)[\\]]".toRegex()
        regexNegative.find(str)?.let {
            if (it.value == str){
                return true
            }
        }
        regexNegativeWithBrackets.find(str)?.let {
            if (it.value == str){
                return true
            }
        }
        return false
    }

    fun deleteSpecSymbolForNegative(str: String) = str.replace("[","").replace("]","")
    fun addSpecSymbolForNegative(int: Int):String{
       return if (int>0) int.toString() else "[$int]"
    }
}
