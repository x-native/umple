// Copyright: All contributers to the Umple Project
// This file is made available subject to the open source license found at:
// http://umple.org/license
//
// Initializes all the tooltips on umpleonline

// This is a dictionary of all the tooltips that are not initialized using the title attribute.
// The are in the following format: 'id: ["tag type", "tooltip HTML"]'
// These tooltips can use full HTML and can be styled using css
var ToolTips = {}

// All custom shortcut types
var undo, redo, toggleTextEditor;

// Some special shortcuts
if (navigator.appVersion.indexOf("Mac")!=-1)
{
  undo = 'cmd-z';
  redo = 'cmd-shift-z';
  toggleTextEditor = 'ctrl-t';
}
else
{
  undo = 'ctrl-z';
  redo = 'ctrl-y';
  toggleTextEditor = 'ctrl-alt-shift-t';
}



ToolTips.tooltipEntries = {
  // save model tooltips
  ttSaveBookmark: ["li", "Click to bookmark this model"],
  ttSaveModel: ["li", "Click to save this model for future editing"],
  buttonCopy: ["li", "Click to open the source for this model"],
  buttonCopyEncodedURL: ["li", "Click to view a url encoded with this model"],  
  buttonCopyLocalBrowser: ["li", "Click to save the model in in your browser local storage for later reloading"],  
  buttonLoadLocalBrowser: ["li", "Click to load the model from what you had previously saved in this browser (above)"],
  buttonDownloadFiles: ["li", "Click to download the currently open .ump files."],
  ttDropboxSaver: ["li", "Click to save your model to dropbox"],

  // start over tooltips
  ttStartOver: ["li", "Click to clear the text editor and the diagram"],

  // load example tooltips
  ttDropboxChooser: ["li", "Click to load an example from dropbox"],

  // diagram editing tools tooltips
  buttonAddClass: ["li", "Click the diagram to add a class </br><b>Shortcut: [c]</b>"],
  buttonAddAssociation: ["li", "Click on two classes to create an association </br><b>Shortcut: [a]</b>"],
  buttonAddGeneralization: ["li", "Click on two classes to create a generalization </br><b>Shortcut: [g]</b>"],
  buttonDeleteEntity: ["li", "Click on a class, association, or generalization to delete that entity </br><b>Shortcut: [DEL]</b>"],
  buttonUndo: ["li", "Click to undo the most recent action </br><b>Shortcut: [" + undo + "]</b>"],
  buttonRedo: ["li", "Click to redo the most recent undone action </br><b>Shortcut: [" + redo + "]</b>"],
  buttonSyncDiagram: ["li", "Click to sync the diagram and the text"],

  // code generation tooltip
  ttGeneratedCodeType: ["li", "Choose the language or diagram to generate"],
  ttGenerateCode: ["li", "Click this button to generate the above code or diagram"],

  // show/hide elements tooltips
  ttShowHideCanvas: ['li', "Show/Hide the diagram </br><b>Shortcut: [ctrl+d]"],
  ttShowHideTextEditor: ['li', "Show/Hide the text editor </br><b>Shortcut: [" + toggleTextEditor + "]"],
  ttShowHideLayoutEditor: ['li', "Show/Hide the layout editor"],
  ttToggleAttributes: ['li', "Show/Hide attributes on class diagrams </br><b>Shortcut: [shift+ctrl+a]"],
  ttToggleMethods: ['li', "Show/Hide the methods on class diagrams </br><b>Shortcut: [ctrl+m]"],
  ttToggleActions: ['li', "Show/Hide the actions on the state diagrams"],
  ttToggleTransitionLabel: ['li', "Show/Hide the transition labels on the state diagrams"],
  ttToggleGuardLabel: ['li', "Show/Hide the guard labels on the state diagrams"],

  // diagram type tooltips
  ttShowEditableClassDiagram: ["li", "Display a graphically editable class diagram </br><b>Shortcut: [ctrl+e]</b>"],
  ttShowJointJSClassDiagram: ["li", "Display a prettier graphically editable class diagram </br><b>Shortcut: [ctrl+j]</b>"],
  ttShowGvClassDiagram: ["li", "Display a class diagram rendered using GraphViz </br><b>Shortcut: [ctrl+g]</b>"],
  ttShowGvStateDiagram: ["li", "Display a state diagram rendered using GraphViz</br><b>Shortcut: [ctrl+s]</b>"],
  ttShowStructureDiagram: ["li", "Display a graphically editable composite structure diagram </br><b>Shortcut: [ctrl+l]</b>"],

  // preferences tooltips
  ttPhotoReady: ["li", "Remove editing handles from diagram"],
  ttManualSync: ["li", "Stop the diagram from syncing automatically"],

  // other views tooltips
  ttYumlImage: ["li", "Generate a class diagram using Yuml in another window"]
};


ToolTips.initTooltips = function()
{  
  // Initialize the tooltips in the above tooltip dictionary
  for(var id in this.tooltipEntries)
  {
    jQuery("#" + id).tooltip({
      items: this.tooltipEntries[id][0],
      content: this.tooltipEntries[id][1],
      show: {delay: 1000},
      position: {my:"right", at:"left", of:"#"+id}
    });
  }

  //Special cases

  //Sets the example dropdown menus tooltip so it does not collide with the
  //contents of the dropdown list
  dropDownTooltips = {
    exampleType: ["li", "Choose an example type to load"],
    itemLoadExamples: ["li", "Choose a class diagram example to load"],
    itemLoadExamples2: ["li", "Choose a state diagram example to load"],
    itemLoadExamples3: ["li", "Choose a composite structure diagram example to load"]
  }

  for(id in dropDownTooltips)
  {
    jQuery("#" + id).tooltip({
      items: dropDownTooltips[id][0],
      content: dropDownTooltips[id][1],
      show: {delay: 1000},
      position: {my:"center+15% bottom", at:"top", of:"#"+id}
    });
  }

  // Sets the rest of the tooltips using the styling provided by the jQuery style.
  // These tooltips use the title attribute as the content
  jQuery(document).tooltip({show: {delay: 1000}});
}
