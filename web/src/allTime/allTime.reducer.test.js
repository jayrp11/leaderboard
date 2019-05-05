import { ALLTIME_SUCCESS, TOGGLE_SEARCH } from "./allTime.actions";
import reducer from "./allTime.reducer";

describe("allTimeReducer", () => {
    it("should store success response", () => {
        const action = { type: ALLTIME_SUCCESS, response: [{
            name: "Test Name 1"
        }]}
        const updatedState = reducer({}, action)
        expect(updatedState.docs.length).toEqual(1)
    })

    it("should toggle search", () => {
        const action = { type: TOGGLE_SEARCH, toggleString: "test string"}
        const updatedState = reducer({}, action)
        expect(updatedState.toggleString).toEqual("test string")
    })
})