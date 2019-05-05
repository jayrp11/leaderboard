import { CALL_API } from "../middleware/api";

export const ALLTIME_REQUEST = "ALLTIME_REQUEST";
export const ALLTIME_SUCCESS = "ALLTIME_SUCCESS";
export const ALLTIME_FAILURE = "ALLTIME_FAILURE";

export const TOGGLE_SEARCH = "TOGGLE_SEARCH";

export const toggleSearch = (
    toggleString
  ) => dispatch =>
    dispatch({
      type: TOGGLE_SEARCH,
      toggleString
    });

const fetchAllTime = params => ({
  [CALL_API]: {
    types: [ALLTIME_REQUEST, ALLTIME_SUCCESS, ALLTIME_FAILURE],
    endpoint: state => {
        return state.allTime.toggleString
    },
    method: "get"
  }
});

export const loadAllTime = (params = {}) => (
  dispatch,
  getState
) => {
  return dispatch(fetchAllTime(params));
};