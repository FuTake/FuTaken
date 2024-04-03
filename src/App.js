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
import { useState, useEffect, useContext, createContext, useReducer, useRef } from "react";
// immer代理解决多层次对象属性更新的问题
import { useImmer } from 'use-immer';

const user = {
  name: 'Hedy Lamarr',
  imageUrl: 'https://i.imgur.com/yXOvdOSs.jpg',
  imageSize: 90,
};

const person = [
  {userName: 'zhangsan', id:1},
  {userName: 'lisi', id:2},
  {userName: 'wangwu', id:3},
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

// 将Bio中的html做为children引入IncludeBio的组件中。children在react中固定认为是<>子标签</>，这里子标签的内容
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

// 放在外面就可以生效
let nextId = 0;
export function UpdateArray(){
  const [userName, setUserName] = useState("");
  // const [userList, setUserList] = useState(...person);
  const [userList, setUserList] = useState([]);
  // 放在里面 nextId++就不会生效
  // let nextId = 0;

  const colors = ["yellow", "black", "blue", "red", "gray", "green", "purple"];

  // 修改列表中指定id的被选状态
  function onToggle(id, seen){
    // 浅拷贝的方式
      const tempList = [...userList];
      const tempUser = tempList.find(
        a => a.id === id
      );
      tempUser.seen = seen;
      setUserList(tempList);
  }

  function onToggleDeep(id, seen){
    // 深拷贝的方式
    setUserList(userList.map(tempUser => {
      if(id === tempUser.id){
        // 这个对象有变动，就返回 创建的新对象。这个时候数组里就会包含原来的老对象和现在创建的新对象
        return {...tempUser, seen: seen};
      }else{
        return tempUser;
      }
    }))
  }

  return (
    <>
      <div><input value={userName} onChange={e => setUserName(e.target.value)} /></div>
      <div>{nextId}</div>
      <div>
        <button onClick={() => {
          setUserList([
            ...userList,
            {id:nextId++, userName: userName, seen: nextId%2 === 1}
          ]);
          console.log(userList);
        }}>提交</button>
      </div>
      
      <div>
        <button onClick={()=>{
          setUserList([
            // 使用slice函数必须带上三个点，这里先截取数组下标0的元素。slice函数不会修改原数组的元素
            ...userList.slice(0, 1),
            // 补充元素做为数组下标1的值
            {id: nextId++, userName: userName},
            // 将数组剩下的元素补充到新数组
            ...userList.slice(1)
          ]);
          console.log(userList);
        }}>插入第二行</button>
      </div>

      <div>
        <button onClick={()=>{
          // reverse会直接修改userList的数据，所以不建议这样使用
          // setUserList(userList.reverse());
          // 这是一种浅拷贝，修改userList的数据会直接影响到tempList的数据，从而影响到setUserList入参的值
          const tempList = [...userList];
          userList[0].userName = '名字被修改';
          tempList.reverse();
          setUserList(tempList);
        }}>
          反转
        </button>
      </div>


      <div>
        <ul>
          {userList.map(value => (
            <>
            {/*  颜色的修改不起作用 */}
              <li key={value.id} id={value.id} style={{color:colors[Math.floor(Math.random * 7)]}}>
                <input type="checkbox" checked={value.seen} onChange={e => {
                  onToggleDeep(
                    value.id,
                    e.target.checked
                  );
                }} />
                {value.userName}
                <button key={value.id} onClick={() => {
                  setUserList(
                    userList.filter(a => a.id !== value.id)
                  );
                }}>删除</button>
              </li>
            </>
          ))}
        </ul>
      </div>
    </>
  );
}


//useReducer 功能不理解 https://react.docschina.org/learn/managing-state 提取状态逻辑到 reducer 中 ⚠

export function Section({ level, children }){
  return (
    <section className='section'>
      <LevelContext.Provider value={level}>
        {/* 通过LevelContext.Provider将value传入Heading组件 */}
        {children}
      </LevelContext.Provider>
    </section>
  );
}

export const LevelContext = createContext(1);

export function Heading({ level = null, children }){
  // 使用useContext将level写在Section上，等价于 将level写在每个 子子标题 的用法
  // 注释掉level = useCon...的语句，相当于不适用useContext实现 子子标题 的动态标签
  // 释放level = useCon...语句，相当于使用useContext实现 除子子标题外 的动态标签
  // if(level == null){
  //   // useContext的调用不能依赖于某种条件，如这里的if
    // level = useContext(LevelContext);
  // }
  switch(level) {
    case 1:
      return <h1>{children}</h1>
    case 2:
      return <h2>{children}</h2>
    case 3:
      return <h3>{children}</h3>
    case 4:
      return <h4>{children}</h4>
    case 5:
      return <h5>{children}</h5>
    case 6:
      return <h6>{children}</h6>
    default:
      return <h7>default {children}</h7>;
  }
}
// 使用useContext让Heading使用最近section中配置的level
export function ContextPractice(){
  return (
    <Section level={1}>
      <Heading>主标题</Heading>
      <Section level={2}>
        <Heading>副标题</Heading>
        <Heading>副标题</Heading>
        <Heading>副标题</Heading>
        <Section level={3}>
          <Heading>子标题</Heading>
          <Heading>子标题</Heading>
          <Heading>子标题</Heading>
          <Section>
            <Heading level={4}>子子标题</Heading>
            <Heading level={4}>子子标题</Heading>
            <Heading level={4}>子子标题</Heading>
          </Section>
        </Section>
      </Section>
    </Section>
  );
}

export default function RefTest(){
  // 被useRef和useState修饰后的变量更像静态变量，不会因为渲染而重置为默认值
  // ref.current会在改变后立刻生效 脱围机制
  let ref = useRef(0);
  // time改变后，会在下次渲染后生效
  const [time, setTime] = useState(0);
  //像是成员变量，每次创建相当于new RefTest，所以这里的temp每次渲染后默认值为0
  let temp = 0;


  function handleClick(){
    ref.current = ref.current + 1;
    // 这个+1会在下次渲染后生效，注释掉该行后，div.id=test标签将不会更新，并且alert和console.log中ref.current=temp，time恒为0
    // 所以不触发渲染 temp就不会重置为默认值，这个过程更像成员变量
    setTime(time + 1);
    temp = temp + 1;
    // console.log和alert显示的time要比ref.current小1，temp则只为1。这是渲染前的RefTest所以ref.current和temp的变更立即奏效，time的变更在下次渲染时奏效
    // 并且使用ref不会让react重新渲染组件，所以ref更适合用于仅事件处理器需要的变量
    alert("你点击了一次， ref.current:" + ref.current + " time:" + time + " temp:" + temp);
    console.log(ref.current + " : " + time + " : " + temp);
  }

  return (
    <>
      <button onClick={handleClick}>点击</button>
      {/* ref.current = time，temp恒为0。 先执行handleClick函数，然后重新渲染（重新渲染相当于new RefTest此时temp被重置，而time则显示为渲染时的数(即新RefTest的值)） */}
      <div id="test">{ref.current + " " + time + " " + temp}</div>
    </>
  );
}