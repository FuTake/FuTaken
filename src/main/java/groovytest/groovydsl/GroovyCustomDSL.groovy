package groovytest.groovydsl

import org.junit.Test

class DateUtil {

    // days，没有实际意义，主要是为了保证上下文的语义连贯。
    static def getDays(Integer self){ self }

    // ago，返回 x 天前的日期
    static Calendar getAgo(Integer self){
        def date =Calendar.instance
        date.add(Calendar.DAY_OF_MONTH,-self)
        date
    }

    // after, 返回 x 天后的日期。
    static Calendar getAfter(Integer self){
        def date = Calendar.instance
        date.add(Calendar.DAY_OF_MONTH,self)
        date
    }

    // at, 可以设定具体的时间。self是执行ago/after传递的Calendar,而time是at(xxx)xxx的参数
    static Date at(Calendar self,Map<Integer,Integer> time){
        assert time.size() == 1
        println time
        def timeEntry = time.find {true}
        self.set(Calendar.HOUR_OF_DAY,(Integer)timeEntry.key)
        self.set(Calendar.MINUTE,(Integer)timeEntry.value)
        self.set(Calendar.SECOND,0)
        self.time
    }
}

use(DateUtil){
    println 5.days.ago.at(10:30) //10是key 30是value
    println 10.days.after.at(13:30)
    println 10.days.after.time
}