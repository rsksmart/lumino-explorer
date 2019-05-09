import React, {Component} from "react";
import {connect} from "react-redux";
import ChannelTable from "../../components/tables/ChannelTable";
import {tableColumnsChannels} from "../../apiModels/tableModelChannels";

class TokenDetailContainer extends Component {


    render=()=> {
        return <div className="container-fluid container-custom-width py-5 px-2 my-4 px-md-5">
            <div className="row mb-4 mt-5">
                <div className="col text-center">
                    <i className="fal fa-coins fa-2x mb-2 text-green"></i>
                    <ul className="list-unstyled">
                        <li className="w-400">
                            {this.props.selectedSuggestion.tokenAddress}
                        </li>
                        <li className="w-600 mb-3">
                            {this.props.selectedSuggestion.tokenName} <span className="w-400">({this.props.selectedSuggestion.symbol})</span>
                        </li>
                    </ul>
                </div>
            </div>
            <div className="row mb-4">
                <div className="col text-center">
                    <ChannelTable data={this.props.selectedSuggestion.channels} columns={tableColumnsChannels} defaultPageSize={5} showPageSizeOptions={false}/>
                </div>
            </div>
        </div>
    }
}

const mapStateToProps = (state) => {
    return {
        selectedSuggestion: state.searchReducer.suggestion,

    }
};

export default connect(mapStateToProps)(TokenDetailContainer);