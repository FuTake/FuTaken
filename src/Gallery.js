function Profile(){
    return (
        <div>This is a profile</div>
    );
}

//默认导出
export default function Gallery() {
    return (
        // export 必须有一个根标签
        <div>
            <h1>Gallery</h1>
            <div><Profile /></div>
            <div><Profile /></div>
            <div><Profile /></div>
        </div>
    );
}
// 具名导出 
export function Gallery2(){
    return (
        <div>
            <h1>Gallery2</h1>
            <div><Profile /></div>
            <div><Profile /></div>
            <div><Profile /></div>
        </div>
    );
}