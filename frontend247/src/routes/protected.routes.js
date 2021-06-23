import React from "react";
import { Redirect, Route } from "react-router-dom";

const ProtectedRoutes = ({ children, ...rest }) => {
    var session_token = localStorage.getItem('user_data')

    return (
        <Route {...rest} render={({ location }) => {
            return session_token !== null ? (
                children
            ) : (
                <Redirect
                    to={{
                        pathname: "/login",
                        state: {
                            from: location,
                        },
                    }}
                />
            );
        }}
        />
    );
};

export default ProtectedRoutes;