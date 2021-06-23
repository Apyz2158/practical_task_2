import axios from "axios";
import React, { useCallback, useEffect, useState } from "react";
import { useHistory } from "react-router-dom";
import { API } from "../config/api";

const Data2 = () => {
    // JSON.stringify
    const history = useHistory();
    const [postData, setpostData] = useState([])
    const [Auth, setAuth] = useState()
    useEffect(() => {
        const authData = JSON.parse(localStorage.getItem("user_data"));
        console.log("Auth: " + authData);
        if (authData) {
            setAuth(authData);
        }

        let URL = API + 'posts/comments';
        const config = {
            headers: {
                "Authorization": `Bearer ${authData.token}`,
                "content-type": "application/json",
            },
        }
        axios.get(URL, config).then(response => {
            console.log(response, "respose log data");
            setpostData(JSON.stringify(response.data.body))
        })
            .catch(error => {
                console.log(error.response.data.code, "error code");
                if (error.response.data.status == 403) {
                    onLogout();
                }
            })
    }, []);

    const onLogout = async () => {
        await localStorage.removeItem("user_data")
        history.push('/login')
    }

    const onBack = () => {
        history.push('/');
    }

    return (

        <>
            <section className="Data1">
                <div className="container">
                    <div className="back_btn">
                        <button onClick={() => onBack()}>Back</button>
                    </div>
                    {postData}
                </div>
            </section>
        </>
    )
}

export default Data2;