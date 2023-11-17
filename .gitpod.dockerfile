FROM gitpod/workspace-full:latest
FROM gitpod/workspace-mysql

ARG JAVA_VERSION=17.0.8

RUN curl -fsSL "https://get.sdkman.io" | bash \
  && bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh \
  && sed -i 's/sdkman_selfupdate_enable=true/sdkman_selfupdate_enable=false/g' /home/gitpod/.sdkman/etc/config \
  && sed -i 's/sdkman_selfupdate_feature=true/sdkman_selfupdate_feature=false/g' /home/gitpod/.sdkman/etc/config \
  && sdk install java 17.0.9.fx-zulu \
  && sdk default java 17.0.9.fx-zulu \
  && sdk install maven"