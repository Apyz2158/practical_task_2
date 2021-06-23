import axios from "axios";
import React, { useContext, useState } from "react";
import { API } from "../config/api";
//tost
import { toast } from "react-toastify";
import { useHistory } from "react-router-dom";
import { Context } from "../data/Context";

const Login = () => {

    const { setIsAuth } = useContext(Context)
    const history = useHistory();
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')

    const onSubmit = () => {
        const body = {
            "email": email,
            "password": password
        }

        let URL = API + "login"
        axios.post(URL, body).then(async resp => {
            console.log(resp, 'login');
            if (resp.data.code === 200) {
                toast.dark(resp.data.message) //message
                await localStorage.setItem("user_data", JSON.stringify(resp.data.body))
                setIsAuth(resp.data.body)
                history.push("/")
            }
        })
            .catch(error => {
                debugger
                console.log(error, 'error here');
                if (error.response.data.code ==400) {
                    toast.dark(error.response.data.message) //message
                }
            })
    }
    return (
        <>
            <section className="loginpage">
                <div className="container">
                    <h3>Login Form</h3>
                    <div className="form">
                        <input
                            type="email"
                            placeholder="Your Email"
                            value={email}
                            onChange={e => setEmail(e.target.value)}
                        />
                        <input
                            type="password"
                            placeholder="Your Password"
                            value={password}
                            onChange={e => setPassword(e.target.value)}
                        />
                    </div>
                    <div className="logiin_button">
                        <button onClick={() => onSubmit()}>Submit</button>
                    </div>
                </div>
            </section>
        </>
    )
}

export default Login;