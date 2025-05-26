package com.company.webdav.view.article;

import com.company.webdav.entity.Article;
import com.company.webdav.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "articles/:id", layout = MainView.class)
@ViewController(id = "Article.detail")
@ViewDescriptor(path = "article-detail-view.xml")
@EditedEntityContainer("articleDc")
public class ArticleDetailView extends StandardDetailView<Article> {
}