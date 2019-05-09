import React, {Component} from "react";

export default class SummaryInfo extends Component {
    render =()=> {
        let luminoChannels = 0;
        let luminoNodes = 0;
        let tokenChannels = 0;
        let tokenNodes = 0;

        if (this.props.paymentInfo) {
            luminoNodes = this.props.paymentInfo.lumino_nodes;
            luminoChannels = this.props.paymentInfo.lumino_channels;
            tokenNodes = this.props.paymentInfo.token_nodes;
            tokenChannels = this.props.paymentInfo.token_channels;
        }

        return  <ul className="text-center list-unstyled last-movements mb-0 text-white px-3 py-3 d-flex d-md-block">
            <li className="pt-md-3 pb-md-2 border-bottom">
                <i className="fal fa-user-friends fa-lg mb-1"></i>
                <p className="mb-1 lh-1">
                    Lumino <br/> Nodes
                </p>
                <p className="m-0 lh-1">
                    <span>{luminoNodes}</span>
                </p>
            </li>
            <li className="pt-md-3 pb-md-2 border-bottom">
                <i className="fal fa-user-friends fa-lg mb-1"></i>
                <p className="mb-1 lh-1">
                    Token<br/> Nodes
                </p>
                <p className="m-0 lh-1">
                    <span>{tokenNodes}</span>
                </p>
            </li>
            <li className="pt-md-3 pb-md-2 border-bottom">
                <i className="fal fa-chart-network fa-lg mb-1"></i>
                <p className="mb-1 lh-1">
                    Lumino <br/> Channels
                </p>
                <p className="m-0 lh-1">
                    <span>{luminoChannels}</span>
                </p>
            </li>
            <li className="pt-md-3 pb-md-2">
                <i className="fal fa-chart-network fa-lg mb-1"></i>
                <p className="mb-1 lh-1">
                    Token <br/> Channels
                </p>
                <p className="m-0 lh-1">
                    <span>{tokenChannels}</span>
                </p>
            </li>
        </ul>
    }
}
