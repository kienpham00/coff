import logo from './logo.svg';
import './App.css';
import { useState } from "react";
import axios from "axios";

const baseUrl = 'http://localhost:8082/';
function App() {
  const [isLogin, setIsLogin] = useState(false);
  const [userData, setUserData] = useState({});

  const onLoginSuccess = data => {
    setIsLogin(true);
    setUserData(data.data);
  }
  const logoutHandler = () => {
    setUserData({});
    setIsLogin(false);
  }

  return (
    <div className="App">
      <div className="min-h-full flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8">
        <div className="max-w-md w-full space-y-8">
          <div>
            <img className="mx-auto h-12 w-auto" src="https://tailwindui.com/img/logos/workflow-mark-indigo-600.svg" alt="Workflow" />
            <h2 className="mt-6 text-center text-3xl font-extrabold text-gray-900">
              Coffee-Sys
            </h2>
          </div>
        </div>
      </div>

      {
        !isLogin ?
          <Login onLoginSuccess={onLoginSuccess} />
          :

          <UserData userData={userData} logout={logoutHandler} />
      }


    </div>

  );
}

export default App;

const UserData = ({ userData, logout }) => {
  return (
    <>
      <h2>
        FullName: {userData.fullName}
      </h2>
      <h2>
        Email: {userData.email}
      </h2>
      <h2>
        Role: {userData.role}
      </h2>
      <button className="font-medium text-indigo-600 hover:text-indigo-500"
        onClick={logout}>Logout</button>
    </>
  )
}

const Login = ({ onLoginSuccess }) => {
  const [err, setErr] = useState(false);
  const [noti, setNoti] = useState("");
  const [isForgotPass, setForgotPass] = useState(false);
  const onSubmit = (event) => {
    //success
    event.preventDefault();

    axios({
      method: "post",
      url: baseUrl + "p/authenticate",
      data: {
        username: event.target.userName.value,
        password: event.target.passWord.value
      }
    }).then(response => {
      setErr(false)
      // sessionStorage.setItem("token",response.data.token);
      onLoginSuccess(response.data);
    }).catch(err => {
      console.error(err)
      setErr(true);
    })
  }

  const resetPass = event => {
    event.preventDefault();
    axios({
      method: "post",
      url: baseUrl + "p/forgotPass/" + event.target.userName.value,
    }).then(response => {

      // sessionStorage.setItem("token",response.data.token);
      setNoti(response.data.message)
    }).catch(err => {
      console.error(err)
      setNoti("Error when reset pass")
    })
  }
  return (
    <div>

      <div className="min-h-full flex items-center justify-center px-4 sm:px-6 lg:px-8">
        <div className="max-w-md w-full ">
          <div>
            {/*<img className="mx-auto h-12 w-auto" src="https://tailwindui.com/img/logos/workflow-mark-indigo-600.svg" alt="Workflow" />*/}
            {/*<h2 className="mt-6 text-center text-3xl font-extrabold text-gray-900">*/}
            {/*  Coffee-Sys*/}
            {/*</h2>*/}
            {isForgotPass ?
              <h2 className="mt-6 text-center text-md font-extrabold text-gray-900">
                Enter your username and get new-pass from mail
              </h2>

              :
              <h2 className="mt-6 text-center text-xl font-extrabold text-gray-900">
                Sign in to your account
              </h2>
            }

            <p className="mt-2 text-center text-sm text-gray-600">
            </p>




          </div>
          {
            isForgotPass ?
              //forgot pass page
              <form className="mt-8 space-y-6" onSubmit={resetPass} >
                <h2 className="text-center text-sm  text-gray-900 text-red-500">{noti}</h2>
                <input type="hidden" name="remember" defaultValue="true" />
                <div className="rounded-md shadow-sm -space-y-px">
                  <div>
                    <label htmlFor="userName" className="sr-only">User Name</label>
                    <input id="userName" name="userName" required className="appearance-none rounded-md relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-t-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm" placeholder="User name" />
                  </div>


                </div>
                <div className="flex items-center justify-between">
                  <div className="flex items-center">
                    {/*<input id="remember-me" name="remember-me" type="checkbox" className="h-4 w-4 text-indigo-600 focus:ring-indigo-500 border-gray-300 rounded" />*/}
                    <label htmlFor="remember-me" className="ml-2 block text-sm text-gray-900">

                    </label>
                  </div>
                  <div className="text-sm">






                    {/*  */}
                    <a onClick={() => {
                      setNoti("")
                      setForgotPass(false)
                    }} href="#"
                      className="font-medium text-indigo-600 hover:text-indigo-500">Back to Sign-in</a>
                  </div>
                </div>
                {err ? <h5 className="text-center text-xl  text-gray-900 text-red-500">
                  Username or Password is incorrect!</h5> : ""}

                <div>
                  <button type="submit" value="Sign in" className="group relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500" >
                    <span className="absolute left-0 inset-y-0 flex items-center pl-3">

                      <svg className="h-5 w-5 text-indigo-500 group-hover:text-indigo-400" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
                        <path fillRule="evenodd" d="M5 9V7a5 5 0 0110 0v2a2 2 0 012 2v5a2 2 0 01-2 2H5a2 2 0 01-2-2v-5a2 2 0 012-2zm8-2v2H7V7a3 3 0 016 0z" clipRule="evenodd" />
                      </svg>
                    </span>
                    Get new password
                  </button>
                </div>
              </form>


              :
              //login page
              <form className="mt-8 space-y-6" onSubmit={onSubmit} >
                <input type="hidden" name="remember" defaultValue="true" />
                <div className="rounded-md shadow-sm -space-y-px">
                  <div>
                    <label htmlFor="userName" className="sr-only">User Name</label>
                    <input id="userName" name="userName" required className="appearance-none rounded-md relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-t-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm" placeholder="Username" />
                  </div>
                  <div>
                    <label htmlFor="passWord" className="sr-only">Password</label>
                    <input id="passWord" name="password" type="passWord" autoComplete="current-password" required className="mt-2 appearance-none rounded-md relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-b-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm" placeholder="Password" />
                  </div>
                </div>
                {err ? <h5 className="text-center text-xl  text-gray-900 text-red-500">
                  Username or Password is incorrect!</h5> : ""}
                <div className="flex items-center justify-between">
                  <div className="flex items-center">
                    <input id="remember-me" name="remember-me" type="checkbox" className="h-4 w-4 text-indigo-600 focus:ring-indigo-500 border-gray-300 rounded" />
                    <label htmlFor="remember-me" className="ml-2 block text-sm text-gray-900">
                      Remember me
                    </label>
                  </div>
                  <div className="text-sm">






                    {/*  */}
                    <a onClick={() => {
                      setForgotPass(true);
                      setErr(false)
                    }} href="#" className="font-medium text-indigo-600 hover:text-indigo-500">Forgot your password?</a>
                  </div>
                </div>
                <div>
                  <button type="submit" value="Sign in" className="group relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500" >
                    <span className="absolute left-0 inset-y-0 flex items-center pl-3">

                      <svg className="h-5 w-5 text-indigo-500 group-hover:text-indigo-400" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
                        <path fillRule="evenodd" d="M5 9V7a5 5 0 0110 0v2a2 2 0 012 2v5a2 2 0 01-2 2H5a2 2 0 01-2-2v-5a2 2 0 012-2zm8-2v2H7V7a3 3 0 016 0z" clipRule="evenodd" />
                      </svg>
                    </span>
                    Sign in
                  </button>
                </div>
              </form>

          }



        </div>
      </div>

    </div>
  )
}
const ForgotPass = () => {
  return (
    <div>
      <h1>kakakaka</h1>
    </div>
  );
}