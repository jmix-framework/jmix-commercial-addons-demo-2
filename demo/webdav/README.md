# Jmix WebDAV Demo

## Overview

The application's data model includes the [Department](src/main/java/com/company/webdav/entity/Department.java) and [Article](src/main/java/com/company/webdav/entity/Article.java) entities. 

The `Article` entity represents articles of the department's knowledge base. It has the `webdavDocument` attribute that is a reference to a file accessible via WebDAV.

The [article-detail-view](src/main/resources/com/company/webdav/view/article/article-detail-view.xml) manages the `webdavDocument` attribute using the `webdavDocumentUpload` component.

The [article-list-view](src/main/resources/com/company/webdav/view/article/article-list-view.xml) displays the `webdavDocument` column using the renderer method defined in 
[ArticleListView](src/main/java/com/company/webdav/view/article/ArticleListView.java).

## Demo Scenario

1. Run the application and go to <http://localhost:8080> in your browser.
2. Log in as `admin` with password `admin`.
3. Open the *Application → Knowledge base* view.
4. Edit an article and upload a file as a new version of the article's document. Check that the version indicator has changed.
5. Open the *WebDAV -> WebDAV documents* view and explore the built-in file management functionality.
6. Configure HTTPS as described in the [documentation](https://docs.jmix.io/jmix/webdav/configuration.html#https).
7. Restart the application and go to <http://localhost:8443> in your browser.
8. Open the *Application → Knowledge base* view.
9. Click on a document link. The file should be opened in a corresponding desktop office application. 


## See Also

- [WebDAV Documentation](https://docs.jmix.io/jmix/webdav/index.html)
- [WebDAV on Marketplace](https://www.jmix.io/marketplace/webdav/)
 
