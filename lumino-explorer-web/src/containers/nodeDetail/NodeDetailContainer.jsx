import React, {Component} from "react";
import {connect} from "react-redux";
import ChannelTable from "../../components/tables/ChannelTable";
import {tableColumnsChannels} from "../../apiModels/tableModelChannels";


class NodeDetailContainer extends Component{
    constructor(props, context) {
        super(props, context);
        this.state = {
            inputToken: "",
            inputAmount: "",
            inputSettleTimeOut: 500,
            tokensSuggestions: [],
        };
    }

    render=()=> {
        return <div className="container-fluid container-custom-width py-5 px-2 my-4 px-md-5">
            <div className="row mb-4 mt-5">
                <div className="col text-center">
                    <i className="fal fa-chart-network fa-2x mb-2 text-green"></i>
                    <ul className="list-unstyled">
                        <li className="w-400">
                            {this.props.suggestion.nodeAddress}
                        </li>
                        <li className="w-600 mb-3">
                            {this.props.suggestion.rnsAddress}
                        </li>
                    </ul>
                </div>
            </div>
            <div className="row mb-4">
                <div className="col text-center">

                </div>
            </div>
            <div className="row mb-4">
                <div className="col text-center">
                    <ChannelTable data={this.props.suggestion.channels} columns={tableColumnsChannels} defaultPageSize={5}  showPageSizeOptions={false}/>
                </div>
            </div>
        </div>
    }
}

const mapStateToProps = (state) => {
    return {
        suggestion: state.searchReducer.suggestion
    }
};

export default connect(mapStateToProps)(NodeDetailContainer);