import { mapStateToProps, AllTimeComponent } from "./allTime";
import { Button, ButtonGroup } from 'reactstrap';
import { shallow, mount, render } from 'enzyme';
import React from "react";

describe('allTime Container', () => {
    it('should map state to props', () => {
        const state = {
            allTime: {
                docs: [
                    {
                        id: 1,
                        name: "Test Name 1"
                    }
                ],
                toggleString: '/api/test'
            }
        }
        const props = mapStateToProps(state)
        expect(props.docs.length).toEqual(1)
        expect(props.toggleString).toEqual('/api/test')
    });

    it("AllTimeComponent", () => {
        const props = {
            docs: [
                {
                    id: 1,
                    name: "Test Name 1",
                    totalPoints: 6,
                    last30Days: 4
                }
            ],
            loadAllTime: jest.fn()
        }
        const wrapper = shallow(<AllTimeComponent {...props}/>);
        expect(wrapper.find(ButtonGroup).children().length).toEqual(2);
        const tableBody = wrapper.find(".lbTable tbody")
        expect(tableBody.children.length).toEqual(1);
    });
});