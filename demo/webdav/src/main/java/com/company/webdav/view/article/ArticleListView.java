package com.company.webdav.view.article;

import com.company.webdav.entity.Article;
import com.company.webdav.view.main.MainView;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.view.*;
import io.jmix.webdav.entity.WebdavDocument;
import io.jmix.webdavflowui.component.WebdavDocumentLink;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "articles", layout = MainView.class)
@ViewController(id = "Article.list")
@ViewDescriptor(path = "article-list-view.xml")
@LookupComponent("articlesDataGrid")
@DialogMode(width = "64em")
public class ArticleListView extends StandardListView<Article> {

    @Autowired
    private UiComponents uiComponents;

    @Supply(to = "articlesDataGrid.webdavDocument", subject = "renderer")
    private Renderer<Article> articlesDataGridWebdavDocumentRenderer() {
        return new ComponentRenderer<>(
                () -> uiComponents.create(WebdavDocumentLink.class),
                (webdavDocumentLink, article) -> {
                    WebdavDocument webdavDocument = article.getWebdavDocument();
                    if (webdavDocument != null) {
                        webdavDocumentLink.setWebdavDocument(webdavDocument);
                    }
                });
    }
}