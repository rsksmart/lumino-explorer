import React from "react";
export const tableColumnsChannels = [
    {
        Header: 'ID',
        accessor: 'channel_identifier',
        maxWidth: 100
    }, {
        Header: 'Partner 1',
        accessor: 'from_rns_address',
        Cell: row => (
            <div>
                <span> {row.original.to_address}</span>

                <small className="d-block"><b>{row.original.to_rns_address}</b></small>
            </div>
        )
    }, {
        Header: 'Partner 2',
        accessor: 'to_rns_address',
        Cell: row => (
            <div>
                <span> {row.original.to_address}</span>

                <small className="d-block"><b>{row.original.to_rns_address}</b></small>
            </div>
        )
    }, {
        Header: 'Token Network',
        accessor: 'token_network_address',
    }
];
