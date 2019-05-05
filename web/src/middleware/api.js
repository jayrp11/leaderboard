export const CALL_API = "Call API";

const API_ROOT = "/api/";

const callApi = (endpoint, method, body) => {
  const fullUrl =
    endpoint.indexOf(API_ROOT) === -1 ? API_ROOT + endpoint : endpoint;

  let config = {};

  if (method) {
    config = { ...config, method };
  }

  if (body) {
    const data = JSON.stringify(body);
    const headers = { ...config.headers, "Content-Type": "application/json" };
    config = { ...config, headers, body: data };
  }

  return fetch(fullUrl, config).then(response =>
    response.json().then(json => {
      if (!response.ok) {
        return Promise.reject(json);
      }
      return json;
    })
  );
};

export default store => next => action => {
  const callAPI = action[CALL_API];
  if (typeof callAPI === "undefined") {
    return next(action);
  }

  let { endpoint } = callAPI;
  const { types, method, body } = callAPI;

  if (typeof endpoint === "function") {
    endpoint = endpoint(store.getState());
  }

  if (typeof endpoint !== "string") {
    throw new Error("Specify a string endpoint URL.");
  }
  if (!Array.isArray(types) || types.length !== 3) {
    throw new Error("Expected an array of three action types.");
  }
  if (!types.every(type => typeof type === "string")) {
    throw new Error("Expected action types to be strings.");
  }

  const actionWith = data => {
    const finalAction = Object.assign({}, action, data);
    delete finalAction[CALL_API];
    return finalAction;
  };

  const [requestType, successType, failureType] = types;
  next(actionWith({ type: requestType }));

  return callApi(endpoint, method, body).then(
    response => {
      next(
        actionWith({
          response,
          type: successType
        })
      );
    },
    error =>
      next(
        actionWith({
          type: failureType,
          errors: error || "Something bad happened"
        })
      )
  );
};
