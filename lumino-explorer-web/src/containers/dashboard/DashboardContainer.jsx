import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import PollingContainer from "../../genericContainers/PollingContainer";
import {pollDashboard} from "../../actions/dashboardActions";
import TokenSelect from "../../components/tokenSelect/TokenSelect";
import SummaryInfo from "../summaryInfo/SummaryInfo";
import FeedContainer from "../feedContainer/FeedContainer";
import Graph from "react-graph-vis";
import {fromWei} from "../../lib/amounts/weiConversion";

const graphOptions = {
    layout: {
        hierarchical: false
    },
    nodes: {
        shape: 'dot',
        size: 10,
        borderWidth: 2,
        color: {
            border: '#1a3f4b',
            background: '#172B36',
            highlight: {
                border: '#70c694',
                background: '#53C676'
            },
            hover: {
                border: '#70c694',
                background: '#53C676'
            }
        },

        font: {
            size: 11,
            face: 'Roboto Condensed',
        },
    },
    edges: {
        title: "test",
        color: "#000000",
        width: 2,
        label: "pepe",
        arrows: {
            to:     {enabled: false, scaleFactor:1, type:'arrow'},
            middle: {enabled: false, scaleFactor:1, type:'arrow'},
            from:   {enabled: false, scaleFactor:1, type:'arrow'}
            }
    },

    height: "400px"
};

class DashboardContainer extends Component{

    constructor(props) {
        super(props);
        this.state = {
            isSummaryNodeActive: false,
            isSummaryChannelActive : false,
            addressFrom: null,
            addressTo: null,
        };
    }

    hideSummaries() {
        this.setState({
            isSummaryNodeActive: false,
            isSummaryChannelActive: false,
            summaryNodeList: null,
            summaryChannelList: null
        });
    }

    showNodeSummary(nodes) {
        if(nodes && (nodes.length === 1)) {
            // we have one node selected
            // we get the index of the selected node
            let nodeIndex = nodes[0];
            let fullNodeList = this.props.dashboardData.nodes;
            let node = fullNodeList[nodeIndex];

            let widget = <div className="fs-14">
                <p className="text-green m-0 w-600">Node Summary</p>
                <p className="m-0"><span className="text-green d-inline-block d-sm-inline">RNS Address: </span> <span className="d-inline-block d-sm-inline">{node.rns_address}</span></p>
                <p className="m-0"><span className="text-green d-inline-block d-sm-inline">RSK Address: </span> <span className="summary-rsk-address d-inline-block d-sm-inline w-99">{node.node_address}</span></p>
            </div>

            this.setState({
                isSummaryNodeActive: true,
                isSummaryChannelActive: false,
                summaryNodeList: widget,
            });
        }
    }

    showChannelSummary(edges,nodes,  nodeGraph) {
        if (edges.length > 0 && (nodes.length === 0)) {
            let widget;
            edges.forEach((channelIdentifier) => {
                nodeGraph.edges.forEach((edgeGraph) => {
                    if (channelIdentifier === edgeGraph.id) {
                        widget = <div className="fs-14">
                            <p className="text-green m-0 w-600">Channel Summary</p>
                            <p className="m-0"><span className="text-green d-inline-block d-sm-inline">ID: </span> <span className="d-inline-block d-sm-inline">{edgeGraph.id}</span></p>
                            <p className="m-0"><span className="text-green d-inline-block d-sm-inline">Participant 1: </span> <span className="d-inline-block d-sm-inline">{edgeGraph.from_rns_address} ({edgeGraph.from_address})</span></p>
                            <p className="m-0"><span className="text-green d-inline-block d-sm-inline">Participant 2: </span> <span className="d-inline-block d-sm-inline">{edgeGraph.to_rns_address} ({edgeGraph.to_address})</span></p>
                            <p className="m-0"><span className="text-green d-inline-block d-sm-inline">Token: </span> <span className="d-inline-block d-sm-inline">{edgeGraph.token_name} ({edgeGraph.token_address})</span></p>
                            <p className="m-0"><span className="text-green d-inline-block d-sm-inline">Balance: </span> <span className="d-inline-block d-sm-inline">{edgeGraph.balance} </span></p>
                            <p className="m-0"><span className="text-green d-inline-block d-sm-inline">Status: </span> <span className="d-inline-block d-sm-inline">{edgeGraph.state}</span></p>
                        </div>
                    }
                });

            });

            this.setState({
                isSummaryChannelActive: true,
                isSummaryNodeActive: false,
                summaryChannelList: widget,
            });
        }

    }

    render=()=>{
        return <PollingContainer
                render={this.renderPolling}
                pollAction={this.pollDashboardContent}
                dueTim={0}
                periodOfScheduler={2000}
            />
    };

    pollDashboardContent =()=> {
        this.props.pollDashboard(this.props.selectedTokenNetwork);
    };

    renderPolling=()=>{
        let summaryInfo = null;
        let tokenSelect = null;

        if (this.props.dashboardData) {
            summaryInfo = <SummaryInfo paymentInfo={this.props.dashboardData.summary} />;
            tokenSelect = <TokenSelect tokenList={this.props.dashboardData.tokens}/>;
        }


        let theNodes = [];
        let theEdges = [];
        if(this.props.dashboardData) {
            if(this.props.dashboardData.nodes) {
                theNodes = this.props.dashboardData.nodes.map((node, idx) => {return {id:idx, label: node.rns_address, address: node.node_address.toLowerCase()}});
            }
            if(this.props.dashboardData.channels) {

                theEdges = this.props.dashboardData.channels.map((channel) => {
                    let source = theNodes.find((n) => {return n.address===channel.from_address.toLowerCase()});
                    let target = theNodes.find((n) => {return n.address===channel.to_address.toLowerCase()});
                    if (source && target) {
                        return {
                            from: source.id,
                            to: target.id,
                            balance: fromWei(channel.total_deposit, channel.token_decimals),
                            from_rns_address : channel.from_rns_address,
                            to_rns_address : channel.to_rns_address,
                            from_address : channel.from_address,
                            to_address : channel.to_address,
                            state : channel.state,
                            token_name : channel.token_name,
                            token_address : channel.token_address,
                            id: channel.channel_identifier,
                        }
                    }else{
                        return null;
                    }

                });
            }
        }
        let nodeGraph = {nodes: theNodes, edges: theEdges};

        return <div className="container-fluid mt-4 mt-lg-0">
                <div className="row justify-content-center mb-md-3">
                    <div className="col-lg-4 col-xl-3 col-md-5 px-md-3">
                        {tokenSelect}
                    </div>
                </div>
                <div className="row align-items-center position-relative">
                    <div className="col-md-auto px-0 px-md-3 mt-3 mt-md-0 summary-container">
                        {summaryInfo}
                    </div>
                    <div className="col-md-8 mx-md-auto bg-white rounded m-3">
                        <Graph
                            graph={nodeGraph}
                            options={graphOptions}
                            events={{
                                select: (event) => {
                                    var { nodes, edges } = event;
                                    this.showNodeSummary(nodes);
                                    this.showChannelSummary(edges, nodes,nodeGraph);
                                }
                            }}
                            getNetwork={network => {
                                //  if you want access to vis.js network api you can set the state in a parent component using this property
                            }}
                        />
                    </div>

                    {this.state.isSummaryNodeActive && <div className="w-100 summary-node fadeInDown animated mb-2 px-3 px-md-0">
                        <div className="bg-white rounded p-3 position-relative">
                            {this.state.summaryNodeList}
                            <button type="button" className="close summary-node__close position-absolute" data-dismiss="alert" aria-label="Close" onClick={() => this.hideSummaries()}>
                                <i className="fal fa-times"> </i>
                            </button>
                        </div>
                    </div>}

                    {this.state.isSummaryChannelActive && <div className="w-100 summary-node fadeInDown animated mb-2 px-3 px-md-0">
                        <div className="bg-white rounded p-3 position-relative">
                            {this.state.summaryChannelList}
                            <button type="button" className="close summary-node__close position-absolute" data-dismiss="alert" aria-label="Close" onClick={() => this.hideSummaries()}>
                                <i className="fal fa-times"> </i>
                            </button>
                        </div>
                    </div>}

                </div>
                <FeedContainer/>
            </div>
    };
}

const mapStateToProps = (state) => {
    return {
        dashboardData: state.dashboardReducer.dashboardData,
        tokens: state.dashboardReducer.tokens,
        selectedTokenNetwork: state.dashboardReducer.selectedTokenNetwork,
    }
};

const mapDispatchToProps = (dispatch) => {
    const actions = {
        pollDashboard: pollDashboard,
    };
    return bindActionCreators(actions, dispatch)
};

export default connect(mapStateToProps, mapDispatchToProps)(DashboardContainer);
