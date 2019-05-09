import React, {Component} from "react";
import Clipboard from 'react-clipboard.js';

export default class TokenItem extends Component {
    render =()=>
        <li className="col-6 my-4">
            <div className="card">
                <div className="card-body align-items-center d-flex p-0">

                    <p className="m-0 px-3 my-3">{this.props.symbol} <br/>
                        <small>{this.props.name}</small></p>

                    <ul className="px-3 my-3 list-unstyled border-left">
                        <li><b>Address:</b> <span className="text-blue data-address align-middle d-inline-block">{this.props.address}</span>
                            <Clipboard data-clipboard-text={this.props.address} className="btn btn-copy-address text-green ml-1">
                                <i className="fal fa-copy align-middle"></i>
                            </Clipboard>
                        </li>
                        <li><b>Balance:</b> <span className="text-blue">{this.props.balance}</span></li>
                    </ul>

                    {this.props.joineable && <button className="btn btn-join-network text-center ml-auto text-white h-100 py-4" onClick={()=>this.props.openJoinNetworkModal(this.props.address)}>
                        <i className="fal fa-plus fa-2x"></i>
                        <span className="d-block">JOIN NETWORK</span>
                    </button>}

                    {!this.props.joineable && <button className="btn btn-join-network text-center ml-auto text-white h-100 py-4" onClick={()=>this.props.openLeaveNetworkModal(this.props.address)}>
                        <i className="fal fa-minus fa-2x"></i>
                        <span className="d-block">LEAVE NETWORK</span>
                    </button>}

                </div>
            </div>
        </li>
}
