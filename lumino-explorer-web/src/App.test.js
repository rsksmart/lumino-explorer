import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import {applyMiddleware, compose, createStore} from "redux";
import thunkMiddleware from "redux-thunk";
import getRootReducer from "./reducers";

const getStore = (initialState)=>{

  const enhancer = compose(
      applyMiddleware(
          thunkMiddleware)
  );
  return createStore(
      getRootReducer,
      initialState,
      enhancer
  );
};


const store = getStore();

it('renders without crashing', () => {
  const app = new App({
    store
  });
  app.render();
});
