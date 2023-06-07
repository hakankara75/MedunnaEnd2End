@create_room @api
  Feature: Ceeate Room By API

    Scenario:  TC01_Create_Room
      Given send post request for creating room
      Then get the response and validate
