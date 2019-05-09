import React, {Component} from "react";
import {Route, Switch} from "react-router-dom";
import ChannelsContainer from "../userChannels/ChannelsContainer";
import DashboardContainer from "../dashboard/DashboardContainer";
import NodeDetailContainer from "../nodeDetail/NodeDetailContainer";
import LuminoHeader from "../headers/HeaderContainer";
import ChannelDetailContainer from "../channelDetail/ChannelDetailContainer";
import TokenDetailContainer from "../tokenDetail/TokenDetailContainer";
import NodesContainer from "../nodesContainer/NodesContainer";


export default class MainContainer extends Component{
    render = () =>
        <div className="">
            <LuminoHeader/>
            <main>
                <Switch>
                    <Route exact path='/' component={DashboardContainer}/>
                    <Route exact path='/dashboard' component={DashboardContainer}/>
                    <Route exact path='/channels' component={ChannelsContainer}/>
                    <Route exact path='/nodes' component={NodesContainer}/>
                    <Route exact path='/nodeDetail' component={NodeDetailContainer}/>
                    <Route exact path='/channelDetail' component={ChannelDetailContainer}/>
                    <Route exact path='/tokenDetail' component={TokenDetailContainer}/>
                </Switch>
            </main>
        </div>
}


