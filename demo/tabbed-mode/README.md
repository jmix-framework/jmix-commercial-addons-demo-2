# Tabbed Application Mode Demo

## Overview

The application's data model includes the [User](src/main/java/com/company/tabbedmode/entity/User.java) entity and the [Project](src/main/java/com/company/tabbedmode/entity/Project.java) entity with the composition of [Task](src/main/java/com/company/tabbedmode/entity/Task.java) entities.

## Demo Scenario

1. Run the application and go to <http://localhost:8107> in your browser.
2. Log in as `admin` with password `admin`.

### Breadcrumbs

1. Open *Application → Projects* view. You will see the project list.
2. Edit any project.
3. Edit any task inside the *Project Detail* view.

Notice that each opened view is added to the breadcrumbs:

![](doc/breadcrumbs.png)

Each breadcrumb is clickable and navigates to a corresponding view, closing all opened after it.

### Force Dialog

1. Open the *Application → Users* view.
2. Edit any user.

Notice that the detail view is opened in the dialog window. This is because the `forceDialog` property is set to the [UserDetailView](src/main/java/com/company/tabbedmode/view/user/UserDetailView.java) using the `@TabbedModeViewProperties` annotation.

### Non-closeable view

1. Open the *Application → Non closeable view*.
Notice that there is no close button (<kbd>x</kbd>) on the tab.
2. *Right-click* on the view tab to open a context menu. Notice that the *close* action is disabled.
3. Click the <kbd>Open view</kbd> button, which opens a new instance of *Non-closeable view* in this tab. Try clicking on the previous instance in the breadcrumbs. Notice that nothing happens.
4. Click on the <kbd>Close</kbd> button inside the view layout to close the view.

This is because the `closeable` property is set to `false` to the [NonCloseableView](src/main/java/com/company/tabbedmode/view/noncloseable/NonCloseableView.java) using the `@TabbedModeViewProperties` annotation. This means that a view can only be closed by invoking the `close()` method of the view.

## See Also

- [Tabbed Application Mode Documentation](https://docs.jmix.io/jmix/tabbed-mode/index.html)
