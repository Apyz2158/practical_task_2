import React from "react";
import { useHistory } from "react-router-dom";

const Landinpage = () => {
    // JSON.stringify
    const history = useHistory();

    const onData1 =() =>{
        history.push('/data1')
    }
    const onData2 = () =>{
        history.push('/data2')
    }
    const onLogout = async () =>{
        await localStorage.removeItem("user_data")
        history.push('/login')
    }
    
    return (
        <>
            <section className="Landinpage">
                <div className="container">
                    <h3>Get data in JSON by clicking on any button.</h3>
                    <div className="getdata_btn">
                        <button onClick={() => onData1()}>Post</button>
                        <button onClick={() => onData2()}>Comments of post</button>
                        <button onClick={() => onLogout()}>Logout</button>
                    </div>
                </div>
            </section>
        </>
    )
}

export default Landinpage;