// Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.jetbrains.plugins.cucumber.java.run;

import com.intellij.execution.application.ApplicationConfigurationType;
import com.intellij.execution.configuration.ConfigurationFactoryEx;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationTypeUtil;
import com.intellij.execution.configurations.ModuleBasedConfiguration;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.components.BaseState;
import com.intellij.openapi.project.Project;
import icons.CucumberJavaIcons;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class CucumberJavaRunConfigurationType extends ApplicationConfigurationType {
  @NonNls
  public static final String ID = "CucumberJavaRunConfigurationType";

  private final ConfigurationFactory cucumberJavaRunConfigurationFactory;

  public CucumberJavaRunConfigurationType() {
    cucumberJavaRunConfigurationFactory = new ConfigurationFactoryEx(this) {
      @Override
      public Icon getIcon() {
        return CucumberJavaRunConfigurationType.this.getIcon();
      }

      @Override
      @NotNull
      public RunConfiguration createTemplateConfiguration(@NotNull Project project) {
        return new CucumberJavaRunConfiguration(getDisplayName(), project, this);
      }

      @Override
      public void onNewConfigurationCreated(@NotNull RunConfiguration configuration) {
        ((ModuleBasedConfiguration)configuration).onNewConfigurationCreated();
      }

      @Override
      public Class<? extends BaseState> getOptionsClass() {
        return CucumberJavaConfigurationOptions.class;
      }
    };
  }

  public static CucumberJavaRunConfigurationType getInstance() {
    return ConfigurationTypeUtil.findConfigurationType(CucumberJavaRunConfigurationType.class);
  }

  @NotNull
  @Override
  public String getDisplayName() {
    return "Cucumber java";
  }

  @Override
  public String getConfigurationTypeDescription() {
    return "Cucumber java";
  }

  @Override
  public Icon getIcon() {
    return CucumberJavaIcons.CucumberJavaRunConfiguration;
  }

  @NotNull
  @Override
  public String getId() {
    return ID;
  }

  @Override
  public ConfigurationFactory[] getConfigurationFactories() {
    return new ConfigurationFactory[]{cucumberJavaRunConfigurationFactory};
  }
}
