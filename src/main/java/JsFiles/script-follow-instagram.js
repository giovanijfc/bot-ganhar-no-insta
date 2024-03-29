let intervalID = null;

const findByTagWithTexts = (tagName, texts) => {
  const elementsToFind = document.getElementsByTagName(tagName);

  for (let i = 0; i < elementsToFind.length; i++) {
    const tag = elementsToFind[i];

    if (texts.includes(tag?.innerText || tag.value)) {
      return tag;
    }
  }

  return;
};

const findByButtonFollow = () =>
  findByTagWithTexts("button", ["Follow Back", "Follow", "Seguir"]);

const findByButtonMessage = () =>
  findByTagWithTexts("button", ["Message", "Enviar mensagem"]);

const treaterFlowBlockFollowAccount = () => {
  const buttonReportIssue = findByTagWithTexts("button", [
    "Relatar um problema",
  ]);
  const buttonOk = findByTagWithTexts("button", ["OK"]);
  const textTitle = findByTagWithTexts("h3", ["Tente novamente mais tarde"]);

  if (buttonReportIssue && buttonOk && textTitle) {
    buttonOk.click();
    window.open("https://www.youtube.com/");
  }

  return buttonReportIssue && buttonOk && textTitle;
};

const start = async () => {
  intervalID = setInterval(() => {
    const buttonFollow = findByButtonFollow();
    const buttonMessage = findByButtonMessage();
    const buttonLike =
      document.querySelector('svg[aria-label="Like"]') ||
      document.querySelector('svg[aria-label="Curtir"]');

    if (buttonLike) {
      buttonLike.parentNode.click();

      setTimeout(() => {
        window.close();
      }, [30000]);

      return;
    }

    if (buttonMessage) {
      setTimeout(() => {
        window.close();
      }, [30000]);

      return;
    }

    if (treaterFlowBlockFollowAccount()) {
      clearInterval(intervalID);

      setTimeout(() => {
        window.close();
      }, [30000]);

      return;
    }

    buttonFollow.click();
  }, [10000]);
};

start();
