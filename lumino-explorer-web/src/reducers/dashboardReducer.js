import createReducer from './helpers/reducerHelper'
import {DASHBOARD_DATA_SUCCEED} from "../actions/types";

const initialState = {
    dashboardData: [],
    selectedTokenNetwork: null,
    tokens:[],
};

const dashboardReducer = createReducer(initialState,
    {
        [DASHBOARD_DATA_SUCCEED](state, action) {
            return {
                ...state,
                dashboardData:  action.data.allData,
                selectedTokenNetwork: action.data.selectedTokenNetwork,
            };
        },
    });

export default dashboardReducer;
