import { useState, useEffect } from "react";


export function UseTime(){
    //让组件记住time这个变量
    const [time, setTime] = useState(() => new Date());
    useEffect(() => {
        const id = setInterval(() => {
            setTime(new Date());
        }, 1000);
        return () => clearInterval(id);
    }, []);
    return time;
}


export function Clock({id, color, time, isBoolean = true, isDel = false}){
    return (
        <h1 style = {{color:color}}>
            {isDel ? (
                <del>
                    {time} {isBoolean && "(*^_^*)"} {id != null && id}
                </del>
            ): (<>{time} {isBoolean && "(*^_^*)"} {id != null && id}</>)}
        </h1>
    );
}