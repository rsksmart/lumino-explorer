import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import {pollNodes} from "../../actions/nodesActions";
import PollingContainer from "../../genericContainers/PollingContainer";
import NodeList from "../../components/nodes/NodeList";

class NodesContainer extends Component {

    getData = () => {
        this.props.pollNodes();
    };

    render = () => {
        return <div>
            <PollingContainer
                render={this.renderPolling}
                pollAction={this.getData}
                dueTim={0}
                periodOfScheduler={2000}
            />

            <div className="container py-5 px-2 my-md-5 px-md-5">
                <NodeList nodeList={this.props.nodes}/>
            </div>

        </div>

    };

    renderPolling = () => {
        return null; // Maybe in a future we can add a toast or something
    };

}

const mapStateToProps = (state) => {
    return {
        nodes: state.nodeReducer.nodes,
        nodesChanged: state.nodeReducer.nodesChanged
    }
};

const mapDispatchToProps = (dispatch) => {
    const actions = {
        pollNodes: pollNodes,
    };
    return bindActionCreators(actions, dispatch)
};

export default connect(mapStateToProps, mapDispatchToProps)(NodesContainer);
