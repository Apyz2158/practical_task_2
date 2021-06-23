import React from "react";
import {
    BrowserRouter as Router,
    Switch,
    Route,
} from "react-router-dom";
import ProtectedRoutes from "./protected.routes";
import { Context } from "../data/Context";
import Login from "../pages/Login";
import Landinpage from "../pages/Lamding";
import Data1 from "../pages/Data1";
import Data2 from "../pages/Data2";


const Routes = () => {
    return (
        <Router>
            <Switch>
                <Route path="/login" component={Login} />
                <ProtectedRoutes exact path="/">
                    <Landinpage />
                </ProtectedRoutes>
                <ProtectedRoutes path="/data1">
                    <Data1 />
                </ProtectedRoutes>
                <ProtectedRoutes path="/data2">
                    <Data2 />
                </ProtectedRoutes>
            </Switch>
        </Router>
    )
}

export default Routes;