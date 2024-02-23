package groovytest.groovydsl


static void main(String... args){
    //添加动态方法
    String.metaClass.custom = {prefix, suffix->
        return (prefix+delegate+suffix).toUpperCase()
    }
    println "hello, world".custom("input:", "args")
    ArrayList lists = new ArrayList(5)
}

class FixedList{
    @Delegate private List list = new ArrayList()
    final int sizeLimit

    /**
     * NOTE: This constructor limits the max size of the list,
     *  not just the initial capacity like an ArrayList.
     */
    FixedList(int sizeLimit){
        this.sizeLimit = sizeLimit
    }

    static void main(String... args){
        List lists = new FixedList(5);
        lists << "zhansan" << "lisi"
        println lists
        def temp = ""
        lists >> temp
        println temp
    }
}