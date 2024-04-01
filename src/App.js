import logo from './logo.svg';
import './App.css';
//引入 默认导出组件 可以任意命名 对应Gallery.js中export default定义的组件
import Custom from './Gallery';
//引入 未导出的组件
import Profile from './Gallery';
//引入 具名导出的组件
import { Gallery2 } from './Gallery';
import { Clock, UseTime } from './Clock.js';
// 任何use开头的函数都是hook,这是个特殊函数
import { useState, useEffect } from "react";
// immer代理解决多层次对象属性更新的问题
import { useImmer } from 'use-immer';

const user = {
  name: 'Hedy Lamarr',
  imageUrl: 'https://i.imgur.com/yXOvdOSs.jpg',
  imageSize: 90,
};

const products = [
  {title: 'zhangsan', id:1},
  {title: 'lisi', id:2},
  {title: 'wangwu', id:3},
];

// 永远不要在组件中嵌套组件
// function的函数名开头必须是字母大写
export function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
        {/* 可以直接这样传参数 */}
        <h1>{connectUserFields("lisi", 7)}</h1>
        <img 
          className='App-logo' 
          alt='logo'
          src={logo} 
          style={{width: user.imageSize,height: user.imageSize}}  //这里的{{}}是正确的
        />
      
      </header>
    </div>
  );
}
// 使用函数将user的属性拼成字符串
// 写逻辑return可以不使用(),写html需要用到();
// 要不要花括号都能编译，不加花括号='王五'和=6为默认值
function connectUserFields(userName='王五', age=6){
  return user.name + user.imageUrl + user.imageSize + " - " + userName + age;
}

// js的标准语法
//export default App;
// export default function Test() {
//  <section><App /></section> 
// }
// 使用外部js中配置的组件Gallery
// export default是默认导出，不使用default时，则为具名导出
export function App2(){
  return (
    <div>
      <h1>App</h1>
      <Custom />
      {/* 也可以使用外部js中 未导入的组件 */}
      <Profile />
      <Gallery2 />
    </div>
  );
}

export function Bio() {
  return (
    <IncludeBio>
      <div className="intro">
        <h1>欢迎来到我的站点！</h1>
      </div>
      <p className="summary">
        你可以在这里了解我的想法。
        <br /><br />
        <b>还有科学家们的<i>照片</i>!</b>
      </p>
    </IncludeBio>
  );
}

// 将Bio中的html做为children引入IncludeBio的组件中
export function IncludeBio({children}){
  // 写html需要用到();
  return (
    <div>
      <h1>IncludeBio</h1>
      {children}
    </div>
  );
}


export function ShowClock({id}){
  const time = UseTime();
  // 使color变量能够在渲染之间被保存
  // 使react使用新数据渲染页面
  const [color, setColor] = useState('lightcoral');
  const [index, setIndex] = useState(0);
  const colors = ['lightcoral', 'midnightblue', 'rebeccapurple', 'blue', 'white', 'gray', 'yellow', 'red'];
  const [bool, setBool] = useState(true);
  const [del, setDel] = useState(false);

  function indexAdd(){
    setIndex(Math.floor(Math.random() * 9));
    setBool(Math.floor(Math.random() * 8)%2 === 0);
    setDel(Math.floor(Math.random() * 8)%2 === 0);
  }

  useEffect(() => {
    const id1 = setInterval(() => {
      indexAdd();
      setColor(colors[index%8]);
    }, 500);
    return () => clearInterval(id1);
  });

  return(
    <div>
      <p>
        选择一个颜色:{' '}
        {/* 这里会使用setColor更新color变量，因为是通过useState返回的变量所以页面也会同步更新 */}
        <select value={color} onChange={e => setColor(e.target.value)}>
          <option value='lightcoral'>浅珊瑚色</option>
          <option value='midnightblue'>午夜蓝</option>
          <option value='rebeccapurple'>丽贝卡紫</option>
          <option value='blue'>蓝色</option>
          <option value='white'>白色</option>
          <option value='gray'>灰色</option>
          <option value='yellow'>黄色</option>
          <option value='red'>红色</option>
        </select>
      </p>
      <Clock id={id} color={color} time={time.toLocaleTimeString()} isBoolean={bool} isDel={del} />
    </div>
  );
}


export function ShowClock2(){

  const clockArray = [2,7,3,12,5,6];
  // 遍历clockArray创建clockArray数组数量个的ShowClock组件
  const showClockList = clockArray.map(value =>{
    return <ShowClock id={value} key={value} />;
  });

  return (
    <div>
      {showClockList}
    </div>
  );
}

export function StateAdd(){
  const [index, setIndex] = useState(0);
  const [index2, setIndex2] = useState(0);

  function addIndex(){
    // 此时两个setIndex执行结果相当于只执行了一次setIndex，
    // 因为index是state变量，对该变量的变更只会在函数完成后渲染完成前完成
    // 所以第一个setIndex将index加1的结果会在addIndex完成后生效。
    setIndex(index + 1);
    setIndex(index + 1);
  }
  function addIndex2(){
    // 使用这种方式可以立刻修改state变量的值，实现index2的两次加1
    // 原理是 将index2+1做为一个函数放入react队列
    // 在渲染时顺序执行队列中的函数从而实现index2重复相加的效果
    setIndex2(index2=> index2+1);
    setIndex2(index2=> index2+1);
  }

  function add(){
    addIndex();
    addIndex2();
  }

  return (
    <div>
      <div>
        {index}
      </div>
      <div>
        {index2}
      </div>
      <button onClick={add}> {index}</button>
    </div>
  );
}

export function MovePoint(){
  //state一个对象
  const [position, setPosition] = useState({x:0, y:0})

  const [offset, setOffset] = useState(0);

  useEffect(()=>{
    const id1 = setInterval(()=>{
      setOffset(Math.floor(Math.random() * 100));
    }, 1000);
    return ()=> clearInterval(id1);
  })
  
  // 怎么实现offset的定时更新和鼠标坐标更新独立
  return (
    <div onPointerMove={e => {
      setPosition({x:e.clientX-offset, y:e.clientY-offset});
      // setOffset(Math.floor(Math.random() * 100));
    }} style={{position:'relative', width:'100vw', height:'100vh'}}>
      <div>{offset}</div>
      <div style={{
        position: 'absolute',
        backgroundColor: 'red',
        borderRadius: '50%',
        transform: `translate(${position.x}px, ${position.y}px)`,
        left: -10,
        top: -10,
        width: 20,
        height: 20,
      }} />
    </div>
  );
}

export function InputUpdate(){
  // const [person, setPerson] = useState({
    const [person, setPerson] = useImmer({  //useImmer会将person对象改为只读
    username:"zhangsan",
    age:15,
    gender:"man",
    remark:"I'm ok",
    remark2:"I'm very ok",
    liveCity:{
        first:"luoyang",
        second:"zhengzhou",
        third:"beijing"
    }
  })
  function changeUserName(e){
    //使用useImmer代理对象修改对象属性，原对象会变为只读，此时执行该方法会抛Cannot assign to read only property 'username' of object '#<Object>'错误
    person.username = e.target.value;
  }
  function changeAge(e){
    setPerson({
      username:person.username,
      age:e.target.age,
      gender:person.gender,
      liveCity:{}
    });
  }
  function changeGender(e){
    // 对象展开语法，先将person对象复制到tempPerson上，在修改tempPerson的gender对象
    const tempPerson = {
      ...person,
      gender:e.target.age
    }
    setPerson(tempPerson);
  }

  // 使用动态命名 根据input的name更改对象中对应name的值
  function changeMark(e){
    setPerson({
      ...person,
      [e.target.name] : e.target.value
    })
  }

  function changeLive(e){
    setPerson({
      ...person,
      liveCity: {
        ...person.liveCity,
        [e.target.name] : e.target.value
      }
    })
  }

  function changeLiveByImmer(e){
    setPerson(value => {
      // value.liveCity.[e.target.name] = e.target.value 不可以
      value.liveCity.third = e.target.value
    })
  }

  return (
    <div>
      {/* 输入框修改内容无效 因为调用changeUserName修改的是渲染前的person对象 */}
      <div>FirstName: <input value = {person.username} onChange={changeUserName}/></div>
      {/* 输入框修改内容有效 */}
      <div>Age: <input value = {person.age} onChange={changeAge}/></div>
      {/* 输入框修改内容有效 */}
      <div>Gender: <input value = {person.gender} onChange={changeGender}/></div>
      {/* 使用动态命名 根据input的name更改对象中对应name的值 */}
      <div>Mark: <input name = "remark" value = {person.remark} onChange={changeMark}/></div>
      <div>Mark2: <input name = "remark2" value = {person.remark2} onChange={changeMark}/></div>
      <div>liveCity: <input name = "first" value = {person.liveCity.first} onChange={changeLive}/></div>
      <div>liveCity: <input name = "second" value = {person.liveCity.second} onChange={changeLive}/></div>
      {/* 使用immer修改深层对象属性 */}
      <div>liveCity: <input name = "third" value = {person.liveCity.third} onChange={changeLiveByImmer}/></div>
    </div>
  );
}


