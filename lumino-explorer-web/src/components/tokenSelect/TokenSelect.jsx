import React, {Component} from "react";
// import {fromWei} from "../../lib/amounts/weiConversion";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import {pollDashboard} from "../../actions/dashboardActions";
import Select from 'react-select';

class TokenSelect extends Component {
    render =()=> {
        // console.log(this.props.selectedTokenNetwork);
        let tokenBarItemList = [];

        if(this.props.tokenList) {
            let tokenList = this.props.tokenList;
            for (let i = 0; i < tokenList.length ; i++) {
                tokenBarItemList.push({
                    value: tokenList[i].network_address,
                    label: tokenList[i].name
                });
            }
        }

        return <Select
            value={this.props.selectedTokenNetwork ? this.props.selectedTokenNetwork : (tokenBarItemList.length>0? tokenBarItemList[0]:null)}
            options={tokenBarItemList}
            className='token-select'
            classNamePrefix="token-select"
            placeholder={'Select Token...'}
            onChange={(val) => {this.props.pollDashboard(val);}}/>
    }
}

const mapDispatchToProps = (dispatch) => {
    const actions = {
        pollDashboard: pollDashboard,
    };
    return bindActionCreators(actions, dispatch)
};


const mapStateToProps = (state) => {
    return {
        selectedTokenNetwork: state.dashboardReducer.selectedTokenNetwork,
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(TokenSelect);
