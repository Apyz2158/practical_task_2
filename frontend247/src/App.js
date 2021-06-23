import { ContextProvider } from "./data/Context";
import Routes from './routes/routes'
import "react-toastify/dist/ReactToastify.css";
import { ToastContainer } from "react-toastify";
import './App.css';

function App() {
  return (
    <ContextProvider>
      <Routes />
      <ToastContainer />
    </ContextProvider>
  );
}

export default App;
