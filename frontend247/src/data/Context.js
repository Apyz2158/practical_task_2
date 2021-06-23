import React, { createContext, useState } from "react";

export const Context = createContext();

export const ContextProvider = ({ children }) => {

    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [isAuth, setIsAuth] = useState(null);



    return (
        <Context.Provider
            value={{
                isLoggedIn,
                setIsLoggedIn,
                isAuth,
                setIsAuth
            }}
        >
            {children}
        </Context.Provider>
    );
};