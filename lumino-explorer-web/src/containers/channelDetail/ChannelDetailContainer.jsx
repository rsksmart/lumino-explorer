import React, {Component} from "react";
import {connect} from "react-redux";


class ChannelDetailContainer extends Component{


    render=()=>{
        return <div className="container-fluid container-custom-width py-5 px-2 my-5">
            <div className="row mb-5 no-gutters">
                <div className="col-md-10 mx-auto position-relative">
                    <hr className="position-absolute channel-line" />
                    <div className="row no-gutters align-items-center justify-content-between">
                        <div className="col-lg-auto text-center">
                            <div className="channel-circle-item d-inline-block shadow-sm position-relative text-center">
                                <i className="fal fa-2x align-middle fa-fw fa-address-card text-white"></i>
                            </div>
                            <p className="text-green overflow-mobile">{this.props.selectedSuggestion.channelFrom}</p>
                        </div>
                        <div className="text-center token-info py-5 py-lg-0">
                            <ul className="list-unstyled mb-0">
                                <li><i className="text-blue fal fa-coins fa-2x"></i></li>
                                <li className="text-blue"><b>{this.props.selectedSuggestion.tokenName}</b></li>
                                <li className="text-blue overflow-mobile">{this.props.selectedSuggestion.tokenAddress}</li>
                                <li></li>
                            </ul>
                        </div>
                        <div className="col-lg-auto text-center">
                            <div className="channel-circle-item d-inline-block shadow-sm position-relative">
                                <i className="fal fa-2x align-middle fa-fw fa-address-card text-white"></i>
                            </div>
                            <p className="text-green overflow-mobile">{this.props.selectedSuggestion.channelTo}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    };

}

const mapStateToProps = (state) => {
    return {
        selectedSuggestion: state.searchReducer.suggestion,
        tokens: state.dashboardReducer.tokens,
    }
};

export default connect(mapStateToProps, null)(ChannelDetailContainer);
