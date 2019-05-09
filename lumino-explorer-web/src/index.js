import registerServiceWorker from './registerServiceWorker';
import React from 'react';
import ReactDOM from 'react-dom';
import { createStore, applyMiddleware, compose } from 'redux';
import getRootReducer from "./reducers/index.js";
import './design/scss/main.scss';

import thunkMiddleware from 'redux-thunk';
import App from "./App";

//Create the store with thunk middleware and the root reducer. Adds the swagger rest client as parameter for thunk
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


const Store = getStore();


ReactDOM.render(<App store={Store}/>, document.getElementById('root'));


registerServiceWorker();
