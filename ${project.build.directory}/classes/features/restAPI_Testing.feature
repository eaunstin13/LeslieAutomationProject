@FunctionalTest @Smoke
Feature: Sanity Test on any site

  @apitest3
  Scenario Outline: Dummy Rest Api GET Students
    Given Get Call to "<url>"
    Then Response Code "<responseMessage>" is validated

    Examples:
      | url      | responseMessage |
      | /posts | 200 |

  Scenario Outline:  Verify Code
    Given Get Call to "<url>"
    Then Response  is array total "<total>"

    Examples:
      | url      | total |
      | /posts | 2    |