import React, {Component} from "react";
import {fromWei} from "../../lib/amounts/weiConversion";

export default class ChannelItem extends Component {
    render =()=> {
        return  <li className="col-12 mb-3">
            <div className="card">
                <div className="card-body d-flex p-0 align-items-center">
                    <ul className="px-3 my-3 list-unstyled">
                        <li><b>ID:</b> <span className="text-blue ml-1">{this.props.id}</span></li>
                        <li><b>Participant 1:</b> <span className="text-blue ml-1"><b>{this.props.from_rns_address}</b> ({this.props.from_address})</span></li>
                        <li><b>Participant 2:</b> <span className="text-blue ml-1"><b>{this.props.to_rns_address}</b> ({this.props.to_address})</span></li>
                        <li><b>Token:</b> <span className="text-blue ml-1"><b>{this.props.token_name}</b> ({this.props.token})</span></li>
                        <li><b>Balance:</b> <span className="text-blue ml-1"> {fromWei(this.props.total_deposit, this.props.token_decimals)} </span> </li>
                        <li><b>Status:</b> {" "} <span className="badge ml-1 badge-success">{this.props.state}</span></li>
                    </ul>

                </div>
            </div>

        </li>
    }
}
