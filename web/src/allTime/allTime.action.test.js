import { toggleSearch, loadAllTime } from "./allTime.actions";

describe('allTime.action', () => {
    it('toggleSearch', () => {
        const dispatch = jest.fn();
        const thunkFunk = toggleSearch("/api/test")
        thunkFunk(dispatch)
        expect(dispatch).toHaveBeenCalledTimes(1);
    })

    it('loadAllTime', () => {
        const dispatch = jest.fn();
        const thunkFunk = loadAllTime()
        thunkFunk(dispatch, {})
        expect(dispatch).toHaveBeenCalledTimes(1);
    })
})