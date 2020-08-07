Information to run this assignment:

UI Scenario : Script will run in both browser(chrome and firefox)

running command :
    gradle runtests
    
To run in headless mode us this :

xvfb-run --auto-servernum --server-args="-screen 0 1024x768x24" gradle runtests


Report will generate under : allure-report/ui/index.html

