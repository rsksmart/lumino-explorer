import React, {Component} from "react";

export default class NodeItem extends Component {
    render =()=> {

        return  <li className="col-12 mb-3">
            <div className="card">
                <div className="card-body d-flex p-0 align-items-center">
                    <ul className="px-3 my-3 list-unstyled">
                        <li><b>RSK Address:</b> <span className="text-blue ml-sm-1">{this.props.node_address}</span></li>
                        <li><b>RNS Address:</b> <span className="text-blue ml-1">{this.props.rns_address}</span> </li>
                        <li><b>Open Channels:</b> <span className="text-blue ml-1">{this.props.openChannelsCount}</span> </li>
                    </ul>

                </div>
            </div>

        </li>
    }
}
