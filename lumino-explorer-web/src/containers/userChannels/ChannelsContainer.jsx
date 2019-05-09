import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import { pollChannels, } from "../../actions/channelsActions";
import PollingContainer from "../../genericContainers/PollingContainer";
import 'react-toastify/dist/ReactToastify.css';
import ChannelList from "../../components/channels/ChannelList";



class ChannelsContainer extends Component{



    getData = ()=>{
        this.props.pollChannels();
    };

    render=()=>{
        return <div>
            <PollingContainer
                render={this.renderPolling}
                pollAction={this.getData}
                dueTim={0}
                periodOfScheduler={2000}
            />

            <div className="container py-5 px-2 my-md-5 px-md-5">
                <ChannelList channelList={this.props.channels} />
            </div>

        </div>

    };

    renderPolling=()=>{
        return null; // Maybe in a future we can add a toast or something
    };

}

const mapStateToProps = (state) => {
    return {
        channels: state.channelReducer.channels,
        channelsChanged: state.channelReducer.channelsChanged,
        tokens: state.dashboardReducer.tokens,
    }
};

const mapDispatchToProps = (dispatch) => {
    const actions = {
        pollChannels: pollChannels,
    };
    return bindActionCreators(actions, dispatch)
};

export default connect(mapStateToProps, mapDispatchToProps)(ChannelsContainer);
